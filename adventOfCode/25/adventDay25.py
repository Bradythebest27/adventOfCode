import networkx

graph=networkx.Graph()
with open(r"C:\Users\18438\Desktop\adventDay25.txt", "r") as file:
    for line in file:
        left, right = line.split(":")
        for node in right.strip().split():
            graph.add_edge(left, node)
            graph.add_edge(node, left)

graph.remove_edges_from(networkx.minimum_edge_cut(graph))
split1, split2=networkx.connected_components(graph)
print(len(split1)*len(split2))