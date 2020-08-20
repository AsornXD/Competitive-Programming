n = input()
l = raw_input().split()
for x in range(len(l)):
    l[x] = int(l[x])
s = ''
for i in l:
    s += str(i) + ' '
print s
while 1:
    c = 0
    for x in range(len(l)-1):
        if l[x] > l[x+1]:
            c+=1
            l1 = l[x]
            l2 = l[x+1]
            l[x] = l2
            l[x+1] = l1
            s = ''
            for i in l:
                s+=str(i)+' '
            print s
    if c == 0:
        break
