import sys
sys.setrecursionlimit(10**6)
for test_case in range(1):
    n = int(input())
    dp = {}
    arr = []
    for _ in range(n):
        arr.append(int(input()))


    def dfs(l, cnt):
        if (l, cnt) in dp:
            return dp[(l, cnt)]
        if l == n:
            return 0
        num = 0
        if cnt == 2:
            num = dfs(l+1, 0)
        else:
            num = max(dfs(l + 1, cnt + 1) + arr[l], dfs(l + 1, 0))
        dp[(l, cnt)] = num
        return num

    print(dfs(0, 0))