
import sys

# t = int(sys.stdin.readline())
input = sys.stdin.readline
for test_case in range(1):
    n, s, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    dp = {}
    ans = -1
    def dfs(l, total):
        global ans
        if (l, total) in dp:
            return dp[(l, total)]
        if l == len(arr):
            ans = max(ans, total)
            return 0
        n1, n2 = -1, -1
        if total + arr[l] <= m:
            n1 = dfs(l+1, total + arr[l])

        if total - arr[l] >= 0:
            n2 = dfs(l+1, total - arr[l])

        dp[(l, total)] = max(n1,n2)
        return dp[(l, total)]

    dfs(0, s)
    print(ans)