import networkx as nx

g = nx.Graph()

with open("b.txt") as f:
    for line in f:
        a, b = line.strip().split('-')
        g.add_edge(a, b)

bigThingy = list(nx.enumerate_all_cliques(g))
sortedBigThingy = sorted(bigThingy[-1])
for thing in sortedBigThingy:
    print(thing + ',', end='')