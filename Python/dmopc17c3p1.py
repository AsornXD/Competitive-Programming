n = input()
l = raw_input().split()
for i in range(len(l)):
    l[i] = int(l[i])
print min(l)