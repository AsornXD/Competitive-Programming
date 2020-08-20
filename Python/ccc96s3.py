def check(iter, str):
    if iter == k:
        if str.count("1") == k and str not in arr:
            arr.append(str)
            print str
        return
    for i in range(len(str)):
        if i != "0":
            newstr = ""
            for j in range(len(str)):
                if j == i:
                    newstr+="1"
                else:
                    newstr+=str[j]
            check(iter+1, newstr)
a = input()
for x in range(a):
    arr = []
    n, k = raw_input().split()
    n = int(n)
    k = int(k)
    str = ""
    print "The bit patterns are"
    for i in range(n):
        str+="0"
    check(0,str)
    if x != a-1:
        print ""
