def getmaxsum(itercount):
    if itercount == k+1:
        return
    dp.append(newdp[:])
    for i in range(n):
        if i-w <= 0:
            if i == 0:
                dp[itercount][-(i+1)] = sums[-(i+1)]
            else:
                dp[itercount][-(i+1)] = max(sums[-(i+1)],dp[itercount][-(i+1)+1])
        else:
            dp[itercount][-(i+1)] = max(dp[itercount][-(i+1)+1],sums[-(i+1)] + dp[itercount-1][-(i+1)+w])
    getmaxsum(itercount+1)
    return
for i in range(input()):
    dp1 = []
    dp2 = []
    n, k, w = raw_input().split()
    n = int(n)
    k = int(k)
    w = int(w)
    pinlist = []
    for j in range(n):
        pinlist.append(input())
    sums = []
    dp = []
    newdp = []
    for j in  range(n):
        if j+w+1 > n:
            sums.append(sum(pinlist[j:]))
        else:
            sums.append(sum(pinlist[j:j+w]))
        newdp.append(0)
    dp.append(newdp)
    getmaxsum(1)
    print dp[-1][0]
