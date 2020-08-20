##N = input()
##l = [[]]
##length = [1]
##for x in range(N):
##    l.append([])
##    length.append(1)
##for x in range(N):
##    X = raw_input().split()
##    if X != ['0']:
##        del X[0]
##        for i in range(len(X)):
##            X[i] = int(X[i])
##        l[x+1] = X
##    else:
##        l[x+1] = [0]
##q = []
##q.append(l[1])
##visited = []
##visited.append(1)
##qx = []
##qx.append(1)
##while len(q) > 0:
##    for x in q[0]:
##        for i in visited:
##            if x == i:
##                break
##        else:
##            visited.append(x)
##            qx.append(x-1)
##            q.append(l[x])
##            if length[qx[0]]+1 < length[x] or length[x] == 1:
##                length[x] = length[qx[0]]+1
##    del q[0]
##    del qx[0]
##if len(visited) == N+1:
##    print 'Y'
##else:
##    print 'N'
##print length[0]
l = []
r = []
for x in range(26):
    l.append([])
while 1:
    s = raw_input()
    if s == "**":
        break
    r.append(s)
    l[ord(s[0])-65].append(s[1])
    l[ord(s[1])-65].append(s[0])
c=0
for y in r:
    nl = []
    for x in l:
        a = []
        for i in x:
            a.append(i)
        nl.append(a)
    nl[ord(y[0]) - 65].pop(nl[ord(y[0]) - 65].index(y[1]))
    nl[ord(y[1]) - 65].pop(nl[ord(y[1]) - 65].index(y[0]))
    q = []
    q.append(nl[0])
    visited = []
    while len(q) > 0:
        for x in q[0]:
            for i in visited:
                if i == x:
                    break
            else:
                q.append(nl[ord(x)-65])
                visited.append(x)
        del q[0]
    if visited.count('B') < 1:
        print y
        c+=1
print "There are",c,"disconnecting roads."
