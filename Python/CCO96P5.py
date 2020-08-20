n, m = raw_input().split()
bfs = []
for x in range(26):
    bfs.append([])
for x in range(int(n)):
    a,b = raw_input().split()
    bfs[ord(a[0])-65].append(ord(b[0])-65)
    bfs[ord(b[0])-65].append(ord(a[0])-65)
for x in range(int(m)):
    s,e = raw_input().split()
    dis = []
    for i in range(26):
        dis.append([-1,''])
    s = ord(s[0])-65
    e = ord(e[0])-65
    q = [s]
    dis[s] = [1,chr(s+65)]
    sl = []
    while len(q) != 0:
        for i in bfs[q[0]]:
            if dis[q[0]][0] + 1 < dis[i][0] or dis[i][0] == -1:
                dis[i][0] = dis[q[0]][0] + 1
                dis[i][1] = dis[q[0]][1] + chr(i+65)
                q.append(i)
        del q[0]
    print dis[e][1]
