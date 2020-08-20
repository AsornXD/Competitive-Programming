def checks(nj,nk):
    if s[nk] != '*':
        return True
    else:
        return False
for i in range(input()):
    h = input()
    w = input()
    bfs = []
    dis = []
    for j in range(h):
        l = []
        ndis = []
        for k in range(w):
            l.append([])
            ndis.append(-1)
        dis.append(ndis)
        bfs.append(l)
    for j in range(h):
        s = raw_input()
        for k in range(len(s)):
            if s[k] == '+':
                nl = []
                if j != 0:
                    if checks(j-1,k):
                        nl.append([j-1,k])
                if j != h-1:
                    if checks(j+1,k):
                        nl.append([j+1,k])
                if k != 0:
                    if checks(j,k-1):
                        nl.append([j,k-1])
                if k != w-1:
                    if checks(j,k+1):
                        nl.append([j,k+1])
                bfs[j][k] = nl
            elif s[k] == '-':
                nl = []
                if k != 0:
                    if checks(j,k-1):
                        nl.append([j,k-1])
                if k != w-1:
                    if checks(j,k+1):
                        nl.append([j,k+1])
                bfs[j][k] = nl
            elif s[k] == '|':
                nl = []
                if j != 0:
                    if checks(j-1,k):
                        nl.append([j - 1, k])
                if j != h - 1:
                    if checks(j+1,k):
                        nl.append([j + 1, k])
                bfs[j][k] = nl
    q = [[0,0]]
    dis[0][0] = 0
    while len(q) != 0:
        check = q[0]
        for j in bfs[check[0]][check[1]]:
            if dis[check[0]][check[1]] + 1 < dis[j[0]][j[1]] or dis[j[0]][j[1]] == -1:
                dis[j[0]][j[1]] = dis[check[0]][check[1]] + 1
                q.append(j)
        del q[0]
    if dis[h-1][w-1] == -1:
        print -1
    else:
        print dis[h-1][w-1]+1