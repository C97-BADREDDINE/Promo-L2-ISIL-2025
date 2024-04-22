import networkx as nx
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import tkinter as tk


# Create a Tkinter window
root = tk.Tk()
root.title("Graph Visualization")
# Set the window size and position

x = root.winfo_screenwidth() // 2
y = root.winfo_screenheight() // 2
root.geometry(f"{root.winfo_screenwidth()}x{root.winfo_screenheight()}+{x}+{y}")

# Define a function to transform coordinates from a graph to a standard coordinate system
def graph_to_standard_coordinate(pos, scale=1, shift=(0, 0)):
    standard_pos = {}
    for node, (x, y) in pos.items():
        standard_pos[node] = (scale * x + shift[0], scale * y + shift[1])
    return standard_pos

# Define a function to transform coordinates from a standard coordinate system to a graph
def standard_to_graph_coordinate(standard_pos, scale=1, shift=(0, 0)):
    pos = {}
    for node, (x, y) in standard_pos.items():
        pos[node] = ((x - shift[0]) / scale, (y - shift[1]) / scale)
    return pos


# Create a figure and canvas for matplotlib
fig = plt.figure()
canvas = FigureCanvasTkAgg(fig, master=root)
canvas_widget = canvas.get_tk_widget()
canvas_widget.pack(side=tk.TOP, fill=tk.BOTH, expand=1)

# Create a graph
G = nx.Graph()

# Label to display mouse coordinates
coord_label = tk.Label(root, text="Mouse coordinates: ")
coord_label.pack(side=tk.BOTTOM)

def clear_all():
    G.clear()
    update_plot()

def onclick(event):
    if event.button == 1:  # Left mouse button clicked
        pos= standard_to_graph_coordinate((event.x, event.y), scale=1, shift=(0, 0))
        x, y = pos
        node_label = len(G.nodes) + 1
        G.add_node(node_label, pos=(x, y))
        print("cordonné ajoutez",x , y)
        update_plot()

def onmotion(event):
    x, y = event.x, event.y
    if x is not None and y is not None:
        coord_label.config(text=f"Mouse coordinates: x={x}, y={y}", font=("Arial", 16), fg="black")

def update_plot():
    plt.clf()
    pos = nx.get_node_attributes(G, 'pos')
    nx.draw(
        G,
        pos=pos,
        with_labels=True,
        node_color="red",
        node_size=3000,
        font_color="white",
        font_size=20,
        font_family="Times New Roman",
        font_weight="bold",
        width=5,
        edge_color="black"
    )
    canvas.draw()

# Set up the plot
plt.margins(0.2)
plt.show
plt.gca().set_aspect('equal', adjustable='box')

update_plot()  # Initial plot

# Connect onclick event
plt.gcf().canvas.mpl_connect('button_press_event', onclick)

# Connect onmotion event
plt.gcf().canvas.mpl_connect('motion_notify_event', onmotion)


# Create and display the "Clear All" button
clear_button = tk.Button(root, text="Clear All", command=clear_all, bg="red", fg="white", font=("Arial", 16), relief=tk.RAISED, bd=5, activebackground="black", activeforeground="white", width=15, height=2, anchor="center", justify="center",cursor="hand2")
clear_button.pack(side=tk.BOTTOM)

# Start the Tkinter event loop
root.mainloop()
