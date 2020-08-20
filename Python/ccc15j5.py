import sys
n = int(sys.stdin.readline())
k = int(sys.stdin.readline())
dp = [[[-1 for z in xrange(n+1)] for y in xrange(k+1)] for i in xrange(n+1)]
def check(cur,mn,pl):
    if dp[pl][cur][mn] != -1:
        return dp[pl][cur][mn]
    elif cur == k-1 and pl >= mn:
        return 1
    elif cur != k:
        num = 0
        for x in range(mn,pl+1):
            num+=check(cur+1,x,pl-x)
        dp[pl][cur][mn] = num
        return num
    return 0
print check(0,1,n)
