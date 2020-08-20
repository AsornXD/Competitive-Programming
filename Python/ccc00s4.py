import sys
n = int(sys.stdin.readline())
a = int(sys.stdin.readline())
l = []
mins = -1
dp = []
for x in range(a):
    l.append(int(sys.stdin.readline()))
for x in range(n):
    dp.append(-1)
l.sort(reverse = True)
iters = 0
s=0
def check(s,iters):
    global mins
    if dp[s] == -1:
        dp[s] = iters
    else:
        return
    iters+=1
    if iters < mins or mins == -1:
        for x in l:
            ss = s+x
            if ss == n:
                mins = iters
                return
            if ss < n:
                check(ss,iters)
    return
check (s,iters)
if mins == -1:
    print "Roberta acknowledges defeat."
else:
    print "Roberta wins in",mins,"strokes."
