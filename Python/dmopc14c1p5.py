h, w = raw_input().split()
start = raw_input().split()
end = raw_input().split()
start[0] = int(start[0])
start[1] = int(start[1])
end[0] = int(end[0])
end[1] = int(end[1])
h = int(h)
w = int(w)
bfs = []
graph = []
dis = []
for x in range(h):
    graph.append(raw_input())
    nd = []
    l = []
    for i in range(w):
        nd.append(-1)
        l.append([])
    dis.append(nd)
    bfs.append(l)
for j in range(h):
    for k in range(w):
        if graph[j][k] != 'X':
            if k != w - 1:
                if graph[j][k + 1] != 'X':
                    bfs[j][k].append([j, k + 1])
            if k != 0:
                if graph[j][k - 1] != 'X':
                    bfs[j][k].append([j, k - 1])
            if j != h - 1:
                if graph[j + 1][k] != 'X':
                    bfs[j][k].append([j + 1, k])
            if j != 0:
                if graph[j - 1][k] != 'X':
                    bfs[j][k].append([j - 1, k])
q = [start]
dis[start[0]][start[1]] = 0
while len(q) != 0:
    for x in bfs[q[0][0]][q[0][1]]:
        if dis[q[0][0]][q[0][1]] + 1 < dis[x[0]][x[1]] or dis[x[0]][x[1]] == -1:
            dis[x[0]][x[1]] = dis[q[0][0]][q[0][1]] + 1
            q.append(x)
    del q[0]
t = []
for x in range(input()):
    tl = raw_input().split()
    if dis[int(tl[0])][int(tl[1])] != -1:
        t.append(dis[int(tl[0])][int(tl[1])])
if dis[end[0]][end[1]] - min(t) <= 0:
    print 0
else:
    print dis[end[0]][end[1]] - min(t)
