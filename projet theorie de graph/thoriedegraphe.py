import networkx as nx
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import tkinter as tk

# Create a Tkinter window
root = tk.Tk()
root.title("Graph Visualization")

# Create a figure and canvas for matplotlib
fig = plt.figure()
canvas = FigureCanvasTkAgg(fig, master=root)
canvas_widget = canvas.get_tk_widget()
canvas_widget.pack(side=tk.TOP, fill=tk.BOTH, expand=1)

# Create a graph
G = nx.Graph()

def onclick(event):
    if event.button == 1:  # Left mouse button clicked
        x, y = event.xdata, event.ydata
        if x is not None and y is not None:
            node_label = len(G.nodes) + 1
            G.add_node(node_label, pos=(x, y))
            update_plot()

def select_node(event):
    if event.button == 3:  # Right mouse button clicked
        x, y = event.xdata, event.ydata
        if x is not None and y is not None:
            node_label = None
            for node, pos in nx.get_node_attributes(G, 'pos').items():
                if (x - pos[0])**2 + (y - pos[1])**2 < 0.1:
                    node_label = node
                    break
            if node_label is not None:
                G.remove_node(node_label)
                update_plot()

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

plt.margins(0.2)
plt.gcf().canvas.mpl_connect('button_press_event', onclick)
plt.gcf().canvas.mpl_connect('button_press_event', select_node)

# Start the Tkinter event loop
root.mainloop()
