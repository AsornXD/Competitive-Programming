for i in range(input()):
    w, h = raw_input().split()
    h = int(h)
    w = int(w)
    l = []
    nodes = []
    steps = []
    for j in range(int(h)):
        newnode = []
        newsteps = []
        for k in range(w):
            newnode.append([])
            newsteps.append(-1)
        l.append(raw_input())
        nodes.append(newnode)
        steps.append(newsteps)
    computer = []
    washroom = []
    for j in range(h):
        for k in range(len(l[j])):
            if l[j][k] == 'C':
                computer = [j,k]
            if l[j][k] == 'W':
                washroom = [j,k]
            newl = []
            if k != w - 1:
                if l[j][k+1] != 'X':
                    newl.append([j,k+1])
            if k != 0:
                if l[j][k-1] != 'X':
                    newl.append([j,k-1])
            if j != h - 1:
                if l[j+1][k] != 'X':
                    newl.append([j+1,k])
            if j != 0:
                if l[j-1][k] != 'X':
                    newl.append([j-1,k])
            nodes[j][k] = newl
    steps[computer[0]][computer[1]] = 0
    q = []
    for j in nodes[computer[0]][computer[1]]:
        q.append(j)
        steps[j[0]][j[1]] = 1
    while len(q) != 0:
        for k in nodes[q[0][0]][q[0][1]]:
            if steps[q[0][0]][q[0][1]] + 1 < steps[k[0]][k[1]] or steps[k[0]][k[1]] == -1:
                steps[k[0]][k[1]] = steps[q[0][0]][q[0][1]] + 1
                q.append(k)
        del q[0]
    if steps[washroom[0]][washroom[1]] < 60 and steps[washroom[0]][washroom[1]] != -1:
        print steps[washroom[0]][washroom[1]]
    else:
        print '#notworth'
