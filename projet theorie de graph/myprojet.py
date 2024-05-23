import random
import tkinter as tk
from tkinter import filedialog, simpledialog
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import matplotlib.pyplot as plt
import networkx as nx
import math
import pickle

root = tk.Tk()
root.title("Graph Visualization")

x = root.winfo_screenwidth() // 2
y = root.winfo_screenheight() // 2
root.geometry(f"{root.winfo_screenwidth()}x{root.winfo_screenheight()}+{x}+{y}")

fig, ax = plt.subplots()
canvas = FigureCanvasTkAgg(fig, master=root)
canvas_widget = canvas.get_tk_widget()
canvas_widget.pack(side=tk.TOP, fill=tk.BOTH, expand=1)

G = nx.Graph()

coord_label = tk.Label(root, text="Mouse coordinates: ")
coord_label.pack(side=tk.BOTTOM)

selected_node = None
selected_nodes = []
selected_edge = None

def clear_all():
    global selected_node, selected_nodes, selected_edge
    G.clear()
    selected_node = None
    selected_nodes = []
    selected_edge = None
    update_plot()

def distance(p1, p2):
    return math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)

def SelectNode(event):
    if event.button == 3:
        global selected_node, selected_nodes, selected_edge
        if event.inaxes:
            x, y = event.xdata, event.ydata
            pos = nx.get_node_attributes(G, 'pos')
            for node in pos:
                if distance((x, y), pos[node]) < 2:
                    selected_node = node
                    if node not in selected_nodes:
                        selected_nodes.append(node)
                    if len(selected_nodes) > 2:
                        selected_nodes.pop(0)
                    selected_edge = None
                    update_plot()
                    return
            for edge in G.edges:
                (x1, y1), (x2, y2) = pos[edge[0]], pos[edge[1]]
                if abs((y2 - y1) * x - (x2 - x1) * y + x2 * y1 - y2 * x1) / distance((x1, y1), (x2, y2)) < 0.5:
                    selected_edge = edge
                    selected_node = None
                    selected_nodes = []
                    update_plot()
                    return

def ondrag(event):
    global selected_node
    if event.button == 3: # Right mouse button clicked
        if event.inaxes and selected_node is not None:
            x, y = event.xdata, event.ydata
            if x is not None and y is not None:
                if selected_node in selected_nodes:
                    selected_nodes.remove(selected_node)
                G.nodes[selected_node]['pos'] = (x, y)
                update_plot()

def onrelease(event):
    global selected_node
    selected_node = None

def onclick(event):
    if event.button == 1: # Left mouse button clicked
        global selected_node, selected_nodes, selected_edge
        if len(selected_nodes) > 0:
            selected_nodes = []
            selected_node = None
        if event.inaxes: # Only if the click is within the plot area
            x, y = event.xdata, event.ydata
            pos = nx.get_node_attributes(G, 'pos')
            too_close = any(distance((x, y), pos[node]) < 2 for node in pos)
            if not too_close and selected_node is None:
                node_label = len(G.nodes) + 1
                G.add_node(node_label, pos=(x, y))
                selected_edge = None
                update_plot()

def onmotion(event):
    if event.inaxes:
        x, y = event.xdata, event.ydata
        if x is not None and y is not None:
            coord_label.config(text=f"Mouse coordinates: x={x:.2f}, y={y:.2f}", font=("Arial", 16), fg="black")

def connect_nodes():
    global selected_nodes, selected_node
    if len(selected_nodes) == 2:
        node1, node2 = selected_nodes
        selected_nodes = []
        selected_node = None
        weight = random.randint(1, 30)
        if not G.has_edge(node1, node2):
            G.add_edge(node1, node2, weight=weight)
        update_plot()

def update_plot():
    ax.clear()
    pos = nx.get_node_attributes(G, 'pos')
    node_colors = []
    for node in G.nodes:
        if G.nodes[node].get('stable_set'):
            node_colors.append("green")
        elif node in selected_nodes:
            node_colors.append("blue")
        else:
            node_colors.append("red")
    
    edge_colors = ["blue" if edge == selected_edge else "black" for edge in G.edges]
    nx.draw(
        G,
        pos=pos,
        with_labels=True,
        node_color=node_colors,
        node_size=3000,
        font_color="white",
        font_size=20,
        font_family="Times New Roman",
        font_weight="bold",
        width=5,
        edge_color=edge_colors,
        ax=ax
    )
    edge_labels = nx.get_edge_attributes(G, 'weight')
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_size=15)
    ax.set_xlim(-10, 10) # Set initial x-axis limits
    ax.set_ylim(-10, 10) # Set initial y-axis limits
    canvas.draw()

def find_prim_mst():
    mst = nx.minimum_spanning_tree(G, algorithm='prim')
    update_graph_with_mst(mst)

def find_kruskal_mst():
    mst = nx.minimum_spanning_tree(G, algorithm='kruskal')
    update_graph_with_mst(mst)

def update_graph_with_mst(mst):
    global G
    pos = nx.get_node_attributes(G, 'pos')
    G = mst
    nx.set_node_attributes(G, pos, 'pos')
    update_plot()

def delete_edge():
    global selected_edge
    if selected_edge is not None:
        G.remove_edge(*selected_edge)
        selected_edge = None
        update_plot()

def delete_node():
    global selected_nodes
    for node in selected_nodes:
        G.remove_node(node)
    update_plot()
    selected_nodes = []
    delete_edge()

def welch_powell():
    global G
    sorted_nodes = sorted(G.degree, key=lambda x: x[1], reverse=True)
    node_colors = {}
    color = 0
    for node, _ in sorted_nodes:
        available_colors = {node_colors[n] for n in G.neighbors(node) if n in node_colors}
        if color not in available_colors:
            node_colors[node] = color
        else:
            color += 1
            node_colors[node] = color

    nx.set_node_attributes(G, node_colors, 'color')
    stable_sets = {}
    for node, color in node_colors.items():
        if color not in stable_sets:
            stable_sets[color] = [node]
        else:
            stable_sets[color].append(node)

    largest_stable_set = max(stable_sets.values(), key=len)
    for node in G.nodes:
        G.nodes[node]['stable_set'] = node in largest_stable_set

    selected_nodes[:] = largest_stable_set
    update_plot()

def save_graph():
    file_path = filedialog.asksaveasfilename(defaultextension=".pickle", filetypes=[("Pickle files", "*.pickle"), ("All files", "*.*")])
    if file_path:
        print(f"Saving graph to {file_path}")  # Debugging print
        pos = nx.get_node_attributes(G, 'pos')
        nx.set_node_attributes(G, pos, 'pos')
        try:
            with open(file_path, 'wb') as f:
                pickle.dump(G, f)
            print(f"Graph saved successfully to {file_path}")  # Debugging print
        except Exception as e:
            print(f"Failed to save graph: {e}")  # Debugging print

def load_graph():
    global G
    file_path = filedialog.askopenfilename(filetypes=[("Pickle files", "*.pickle"), ("All files", "*.*")])
    if file_path:
        print(f"Loading graph from {file_path}")  # Debugging print
        try:
            with open(file_path, 'rb') as f:
                G = pickle.load(f)
            update_plot()
            print(f"Graph loaded successfully from {file_path}")  # Debugging print
        except Exception as e:
            print(f"Failed to load graph: {e}")  # Debugging print

update_plot()

fig.canvas.mpl_connect('button_press_event', onclick)
fig.canvas.mpl_connect('motion_notify_event', onmotion)
fig.canvas.mpl_connect('button_press_event', SelectNode)
fig.canvas.mpl_connect('motion_notify_event', ondrag)
fig.canvas.mpl_connect('button_release_event', onrelease)

button_frame = tk.Frame(root)
button_frame.pack(side=tk.BOTTOM)

clear_button = tk.Button(button_frame, text="Clear All", command=clear_all, bg="red", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
clear_button.pack(side=tk.LEFT)

connect_button = tk.Button(button_frame, text="Connect Nodes", command=connect_nodes, bg="green", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
connect_button.pack(side=tk.LEFT)

prim_button = tk.Button(button_frame, text="Find Prim MST", command=find_prim_mst, bg="blue", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
prim_button.pack(side=tk.LEFT)

kruskal_button = tk.Button(button_frame, text="Find Kruskal MST", command=find_kruskal_mst, bg="purple", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
kruskal_button.pack(side=tk.LEFT)

delete_button = tk.Button(button_frame, text="Delete", command=delete_node, bg="orange", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
delete_button.pack(side=tk.LEFT)

welch_powell_button = tk.Button(button_frame, text="Welch-Powell", command=welch_powell, bg="yellow", fg="black", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
welch_powell_button.pack(side=tk.LEFT)

save_button = tk.Button(button_frame, text="Save Graph", command=save_graph, bg="lightblue", fg="black", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
save_button.pack(side=tk.LEFT)

load_button = tk.Button(button_frame, text="Load Graph", command=load_graph, bg="lightgreen", fg="black", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
load_button.pack(side=tk.LEFT)

root.mainloop()
