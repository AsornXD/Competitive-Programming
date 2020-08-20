n,m,b,q = raw_input().split()
n = int(n)
m = int(m)
b = int(b)
q = int(q)
bfs = []
dis = []
for x in range(n):
    bfs.append([])
    dis.append(-1)
dis[b-1] = 0
for x in range(m):
    a,y,z = raw_input().split()
    a = int(a)
    y = int(y)
    z = int(z)
    bfs[a-1].append([y-1,z])
    bfs[y-1].append([a-1,z])

Q = [b-1]
while len(Q) != 0:
    for x in bfs[Q[0]]:
        if dis[Q[0]] + x[1] < dis[x[0]] or dis[x[0]] == -1:
            dis[x[0]] = dis[Q[0]] + x[1]
            Q.append(x[0])
    del Q[0]
for x in range(q):
    print dis[input()-1]
