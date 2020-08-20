N, M = raw_input().split()
N = int(N)
M = int(M)
graph = []
for x in range(N):
    graph.append([])
for x in range(M):
    X, Y = raw_input().split()
    X = int(X)-1
    Y = int(Y)-1
    graph[X].append(Y)
p, q = raw_input().split()
p = int(p)-1
q = int(q)-1
dis = []
for x in range(N):
    dis.append(-1)
que = [p]
dis[p] = 0
while len(que) != 0:
    for x in graph[que[0]]:
        if dis[x] == -1:
            dis[x] = 0
            que.append(x)
    del que[0]
if dis[q] == 0:
    print 'yes'
else:
    dis = []
    for x in range(N):
        dis.append(-1)
    que = [q]
    dis[q] = 0
    while len(que) != 0:
        for x in graph[que[0]]:
            if dis[x] == -1:
                dis[x] = 0
                que.append(x)
        del que[0]
    if dis[p] == 0:
        print 'no'
    else:
        print 'unknown'
