import tkinter as tk
from tkinter import simpledialog
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import matplotlib.pyplot as plt
import networkx as nx
import math

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

def clear_all():
    global selected_node, selected_nodes
    G.clear()
    selected_node = None
    selected_nodes = []
    update_plot()

def distance(p1, p2):
    return math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)

def SelectNode(event):
    global selected_node, selected_nodes
    if event.inaxes:
        x, y = event.xdata, event.ydata
        pos = nx.get_node_attributes(G, 'pos')
        for node in pos:
            if distance((x, y), pos[node]) < 2:
                selected_node = node
                if node in selected_nodes :
                    selected_nodes.remove(node)  
                elif node not in selected_nodes:
                    selected_nodes.append(node)  
                if len(selected_nodes) > 2:
                    selected_nodes.pop(0)
                update_plot()
                break

def ondrag(event):
    global selected_node
    if event.inaxes and selected_node is not None:
        x, y = event.xdata, event.ydata
        if x is not None and y is not None:
            G.nodes[selected_node]['pos'] = (x, y)
            update_plot()

def onrelease(event):
    global selected_node
    selected_node = None

def onclick(event):
    if event.button == 1:  # Left mouse button clicked
        if event.inaxes:  # Only if the click is within the plot area
            x, y = event.xdata, event.ydata
            pos = nx.get_node_attributes(G, 'pos')
            too_close = any(distance((x, y), pos[node]) < 2 for node in pos)
            if not too_close and selected_node is None:
                node_label = len(G.nodes) + 1
                G.add_node(node_label, pos=(x, y))
                update_plot()

def onmotion(event):
    if event.inaxes:
        x, y = event.xdata, event.ydata
        if x is not None and y is not None:
            coord_label.config(text=f"Mouse coordinates: x={x:.2f}, y={y:.2f}", font=("Arial", 16), fg="black")

def connect_nodes():
    if len(selected_nodes) == 2:
        node1, node2 = selected_nodes
        weight = simpledialog.askfloat("Input", f"Enter weight for edge between {node1} and {node2}:")
        if weight is not None:
            G.add_edge(node1, node2, weight=weight)
            update_plot()

def update_plot():
    ax.clear()
    pos = nx.get_node_attributes(G, 'pos')
    node_colors = ["blue" if node in selected_nodes else "red" for node in G.nodes]
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
        edge_color="black",
        ax=ax
    )
    edge_labels = nx.get_edge_attributes(G, 'weight')
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_size=15)
    ax.set_xlim(-10, 10)  # Set initial x-axis limits
    ax.set_ylim(-10, 10)  # Set initial y-axis limits
    canvas.draw()

update_plot()

fig.canvas.mpl_connect('button_press_event', onclick)
fig.canvas.mpl_connect('motion_notify_event', onmotion)
fig.canvas.mpl_connect('button_press_event', SelectNode)
fig.canvas.mpl_connect('motion_notify_event', ondrag)
fig.canvas.mpl_connect('button_release_event', onrelease)

clear_button = tk.Button(root, text="Clear All", command=clear_all, bg="red", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
clear_button.pack(side=tk.BOTTOM)

connect_button = tk.Button(root, text="Connect Nodes", command=connect_nodes, bg="green", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
connect_button.pack(side=tk.BOTTOM)

root.mainloop()
