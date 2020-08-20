h,w,t = raw_input().split()
h = int(h)
w = int(w)
t = int(t)
dis = []
bfs = []
for x in range(h):
    nd = []
    l = []
    for i in range(w):
        nd.append(1000)
        l.append([])
    bfs.append(l)
    dis.append(nd)
def checkdis(startpos):
    q = [startpos]
    ndis = []
    for x in dis:
        l = []
        for i in x:
            l.append(i)
        ndis.append(l)
    ndis[q[0][0]][q[0][1]] = 0
    while len(q) != 0:
        for i in bfs[q[0][0]][q[0][1]]:
            if ndis[q[0][0]][q[0][1]] + 1 < ndis[i[0]][i[1]]:
                q.append(i)
                ndis[i[0]][i[1]] = ndis[q[0][0]][q[0][1]] + 1
        del q[0]
    return ndis
graph = []
for x in range(h):
    graph.append(raw_input())
chl = []
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
        if graph[j][k] == 'G':
            griffy = [j,k]
        if graph[j][k] == 'H':
            chl.append([j,k])
def recurse(cords,counter,hl):
    if len(hl) == 0:
        return counter
    gdis = checkdis(cords)
    counts = []
    for x in hl:
        nhl = []
        for i in hl:
            nl = []
            for j in i:
                nl.append(j)
            nhl.append(nl)
        del nhl[nhl.index(x)]
        counts.append(recurse(x,counter+gdis[x[0]][x[1]],nhl))
    return min(counts)
print recurse(griffy,0,chl)
