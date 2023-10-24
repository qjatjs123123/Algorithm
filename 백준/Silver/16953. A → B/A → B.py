import sys


for test_case in range(1):
    a, b = map(int, sys.stdin.readline().split())
    dp = {}
    def dfs(num):
        if num > b:
            return float("inf")
        if num == b:
            return 1
        if num in dp:
            return dp[num]

        dp[num] = min(dfs(num*2) + 1, dfs(num*10 + 1) + 1)
        return dp[num]

    ans = dfs(a)
    if ans == float("inf"):
        print(-1)
    else:
        print(ans)
