import random
import tkinter as tk
from tkinter import Menu, filedialog, simpledialog, messagebox
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import matplotlib.pyplot as plt
import networkx as nx
import math
import pickle


graph_type = messagebox.askquestion("Graph Type", "Do you want to create a directed graph (Graph Orientée)?", icon='question')
if graph_type == 'yes':
    G = nx.DiGraph()
else:
    G = nx.Graph()

root = tk.Tk()
root.title("Graph Visualization")

x = root.winfo_screenwidth() // 2
y = root.winfo_screenheight() // 2
root.geometry(f"{root.winfo_screenwidth()}x{root.winfo_screenheight()}+{x}+{y}")

fig, ax = plt.subplots()
canvas = FigureCanvasTkAgg(fig, master=root)
canvas_widget = canvas.get_tk_widget()
canvas_widget.pack(side=tk.TOP, fill=tk.BOTH, expand=1)

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

def connect_node(event):
    if (event.button == 2):
        connect_nodes()

def update_plot():
    ax.clear()
    pos = nx.get_node_attributes(G, 'pos')
    node_colors = []
    for node in G.nodes:
        if G.nodes[node].get('stable_set'):
            node_colors.append("orange")
        elif node in selected_nodes:
            node_colors.append("blue")
        elif 'color' in G.nodes[node]:  # Check if the node has a 'color' attribute
            color_index = G.nodes[node]['color']
            color_list = ['red', 'green', 'blue', 'yellow', 'purple', 'orange', 'pink', 'cyan', 'magenta', 'lime']
            node_colors.append(color_list[color_index % len(color_list)])  # Assign a color from the list
        else:
            node_colors.append("red")
    
    edge_colors = ["blue" if edge == selected_edge else "black" for edge in G.edges]
    
    if graph_type == 'yes':
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
            ax=ax,
            arrows=True,
            arrowstyle='-|>'
        )
    else:
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
            ax=ax,
            arrows=False  # Ensure arrows are disabled for undirected graphs
        )
    
    edge_labels = nx.get_edge_attributes(G, 'weight')
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_size=15)
    ax.set_xlim(-10, 10)  # Set initial x-axis limits
    ax.set_ylim(-10, 10)  # Set initial y-axis limits
    canvas.draw()

def find_dijkstra_shortest_path():
    if G.number_of_nodes() > 0:
        start_node = simpledialog.askinteger("Input", "Enter the starting node:")
        if start_node in G.nodes:
            step_by_step_shortest_path(nx.single_source_dijkstra, start_node)

def find_bellman_ford_shortest_path():
    if G.number_of_nodes() > 0:
        start_node = simpledialog.askinteger("Input", "Enter the starting node:")
        if start_node in G.nodes:
            step_by_step_shortest_path(nx.single_source_bellman_ford, start_node)

def step_by_step_shortest_path(algorithm, start_node):
    pos = nx.get_node_attributes(G, 'pos')
    edges_in_path = set()
    paths = {}

    def update_edges_colors():
        edge_colors = []
        for edge in G.edges:
            if edge in edges_in_path or (edge[1], edge[0]) in edges_in_path:
                edge_colors.append("orange")
            else:
                edge_colors.append((128/255, 128/255, 128/255, 0.05))
        return edge_colors

    for target_node in G.nodes:
        if target_node == start_node:
            continue
        _, path = algorithm(G, start_node, target_node)
        for i in range(len(path) - 1):
            edges_in_path.add((path[i], path[i+1]))
            edge_colors = update_edges_colors()
            ax.clear()
            nx.draw(G, pos, with_labels=True, font_weight="bold", node_color="red", font_color="white", edge_color=edge_colors, node_size=3000, font_size=20, width=5, ax=ax)
            nx.draw_networkx_edge_labels(G, pos, edge_labels=nx.get_edge_attributes(G, 'weight'), font_size=20, font_family="Times New Roman", font_weight="bold", ax=ax)
            canvas.draw()
            canvas.get_tk_widget().update()
            root.after(1000)

def step_by_step_mst(edges):
    pos = nx.get_node_attributes(G, 'pos')
    mst_edges = []
    for edge in edges:
        mst_edges.append((edge[0], edge[1]))
        ax.clear()
        edge_colors = ["orange" if (u, v) in mst_edges or (v, u) in mst_edges else (128/255, 128/255, 128/255, 0.05) for u, v in G.edges]
        nx.draw(G, pos, with_labels=True, font_weight="bold", node_color="red", font_color="white", edge_color=edge_colors, node_size=3000, font_size=20, width=5, ax=ax)
        nx.draw_networkx_edge_labels(G, pos, edge_labels=nx.get_edge_attributes(G, 'weight'), font_size=20, font_family="Times New Roman", font_weight="bold", ax=ax)
        canvas.draw()
        canvas.get_tk_widget().update()
        root.after(1000)

def find_prim_mst():
    if nx.is_empty(G):
        messagebox.showerror("Error", "The graph is empty.")
        return
    try:
        mst = nx.minimum_spanning_tree(G, algorithm='prim')
        edges = list(mst.edges(data=True))
        step_by_step_mst(edges)
    except Exception as e:
        messagebox.showerror("Error", f"Failed to find MST using Prim's algorithm: {e}")

def find_kruskal_mst():
    edges = list(nx.minimum_spanning_edges(G, algorithm='kruskal', data=False))
    step_by_step_mst(edges)

def find_maximum_spanning_tree():
    if G.number_of_edges() > 0:
        inverted_G = G.copy()
        for u, v, d in inverted_G.edges(data=True):
            d['weight'] = -d['weight']
        mst_edges = nx.minimum_spanning_edges(inverted_G, algorithm='kruskal', data=False)
        original_edges = [(u, v, -data['weight']) for u, v in mst_edges for data in [inverted_G.get_edge_data(u, v)]]
        step_by_step_mst(original_edges)


def update_graph_with_paths(paths):
    global G
    new_graph = nx.DiGraph() if graph_type == 'yes' else nx.Graph()
    pos = nx.get_node_attributes(G, 'pos')
    for node, path in paths.items():
        if len(path) > 1:
            for i in range(len(path) - 1):
                new_graph.add_edge(path[i], path[i+1], weight=G[path[i]][path[i+1]]['weight'])
    nx.set_node_attributes(new_graph, pos, 'pos')
    G = new_graph
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

def welsh_powell():
    global G
    # Sort vertices by decreasing degree
    sorted_vertices = sorted(G.nodes(), key=lambda x: G.degree(x), reverse=True)
    
    # Initialize the color assignment dictionary
    color_assignment = {}
    
    # Initialize the first color
    current_color = 0
    
    for vertex in sorted_vertices:
        # If the vertex is already colored, continue to the next one
        if vertex in color_assignment:
            continue
        
        # Assign the current color to the vertex
        color_assignment[vertex] = current_color
        
        # Assign the current color to all possible vertices
        for neighbor in sorted_vertices:
            if neighbor not in color_assignment:
                # Check if the neighbor can be colored with the current color
                can_color = True
                for adjacent in G.neighbors(neighbor):
                    if adjacent in color_assignment and color_assignment[adjacent] == current_color:
                        can_color = False
                        break
                if can_color:
                    color_assignment[neighbor] = current_color
        
        # Move to the next color
        current_color += 1
    
    # Update the graph with color assignments
    nx.set_node_attributes(G, color_assignment, 'color')
    
    update_plot()
    return color_assignment

def save_graph():
    file_path = filedialog.asksaveasfilename(defaultextension=".pickle", filetypes=[("Pickle files", "*.pickle"), ("All files", "*.*")])
    if file_path:
        pos = nx.get_node_attributes(G, 'pos')
        nx.set_node_attributes(G, pos, 'pos')
        try:
            with open(file_path, 'wb') as f:
                pickle.dump(G, f)
        except Exception as e:
            messagebox.showerror("Save Error", f"Failed to save graph: {e}")

def load_graph():
    global G
    file_path = filedialog.askopenfilename(filetypes=[("Pickle files", "*.pickle"), ("All files", "*.*")])
    if file_path:
        try:
            with open(file_path, 'rb') as f:
                G = pickle.load(f)
            update_plot()
        except Exception as e:
            messagebox.showerror("Load Error", f"Failed to load graph: {e}")

update_plot()

my_menu = Menu(root)
root.config(menu=my_menu)

# Create a menu item

file_menu = Menu(my_menu, tearoff=False)
my_menu.add_cascade(label="File", menu=file_menu)
file_menu.add_command(label="Save", command=save_graph)
file_menu.add_separator()
file_menu.add_command(label="Open File", command=load_graph)
file_menu.add_separator()
file_menu.add_command(label="Exit", command=root.quit)
file_menu.configure(font=("Arial", 16))
my_menu.configure(font=("Arial", 16), bg="white", fg="black", activebackground="black", activeforeground="white", relief=tk.RAISED, bd=5, cursor="hand2", borderwidth=5)

fig.canvas.mpl_connect('button_press_event', onclick)
fig.canvas.mpl_connect('motion_notify_event', onmotion)
fig.canvas.mpl_connect('button_press_event', SelectNode)
fig.canvas.mpl_connect('motion_notify_event', ondrag)
fig.canvas.mpl_connect('button_release_event', onrelease)
fig.canvas.mpl_connect('button_press_event', connect_node)

button_frame = tk.Frame(root)
button_frame.pack(side=tk.BOTTOM)

clear_button = tk.Button(button_frame, text="Clear All", command=clear_all, bg="red", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
clear_button.pack(side=tk.LEFT)

connect_button = tk.Button(button_frame, text="Connect Nodes", command=connect_nodes, bg="green", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
connect_button.pack(side=tk.LEFT)

if graph_type == 'yes':
    dijkstra_button = tk.Button(button_frame, text="Dijkstra SP", command=find_dijkstra_shortest_path, bg="blue", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
    dijkstra_button.pack(side=tk.LEFT)

    bellman_ford_button = tk.Button(button_frame, text="Bellman-Ford", command=find_bellman_ford_shortest_path, bg="purple", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
    bellman_ford_button.pack(side=tk.LEFT)
else:
    prim_button = tk.Button(button_frame, text="Find Prim MST", command=find_prim_mst, bg="blue", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
    prim_button.pack(side=tk.LEFT)

    kruskal_button = tk.Button(button_frame, text="Find Kruskal MST", command=find_kruskal_mst, bg="purple", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
    kruskal_button.pack(side=tk.LEFT)

    Maximam_spanning_tree_button = tk.Button(button_frame, text="Maximam Spanning Tree", command=find_maximum_spanning_tree, bg="purple", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
    Maximam_spanning_tree_button.pack(side=tk.LEFT)

delete_button = tk.Button(button_frame, text="Delete", command=delete_node, bg="orange", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
delete_button.pack(side=tk.LEFT)

welch_powell_button = tk.Button(button_frame, text="Welch-Powell", command=welsh_powell, bg="yellow", fg="black", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center", cursor="hand2")
welch_powell_button.pack(side=tk.LEFT)

root.mainloop()
