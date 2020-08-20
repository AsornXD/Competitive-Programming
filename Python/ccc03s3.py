def getroomsize(i,j):
    cursize = 1
    q = [[i, j]]
    vis.append([i,j])
    while q != []:
        loc = graph[q[0][0]][q[0][1]]
        for x in loc:
            if x not in vis:
                cursize += 1
                q.append(x)
                vis.append(x)
        del q[0]
    return cursize
space = input()
r = input()
c = input()
inputlist = []
graph = []
for x in range(r):
    inputlist.append(raw_input())
    curl = []
    for j in range(c):
        curl.append([])
    graph.append(curl)
for i in range(r):
    for j in range(c):
        if inputlist[i][j] != 'I':
            if i != 0:
                if inputlist[i-1][j] != 'I':
                    graph[i][j].append([i-1,j])
            if i != r-1:
                if inputlist[i+1][j] != 'I':
                    graph[i][j].append([i+1,j])
            if j != 0:
                if inputlist[i][j-1] != 'I':
                    graph[i][j].append([i,j-1])
            if j != c-1:
                if inputlist[i][j+1] != 'I':
                    graph[i][j].append([i,j+1])
vis = []
rc = 0
sizes = []
for i in range(r):
    for j in range(c):
        if [i,j] not in vis:
            if inputlist[i][j] == '.':
                sizes.append(getroomsize(i,j))
boardcount = 0
space2 = space
while 1:
    if sizes == []:
        extra = space - boardcount
        print rc, "rooms,", extra, "square metre(s) left over"
        break
    ind = sizes.index(max(sizes))
    if boardcount + sizes[ind] == space:
        print rc+1, "rooms, 0 square metre(s) left over"
        break
    if boardcount + sizes[ind] > space:
        extra = space - boardcount
        if rc == 1:
            print rc, "room,", extra, "square metre(s) left over"
        else:
            print rc,"rooms,",extra,"square metre(s) left over"
        break
    else:
        boardcount+=sizes[ind]
    rc += 1
    del sizes[ind]

