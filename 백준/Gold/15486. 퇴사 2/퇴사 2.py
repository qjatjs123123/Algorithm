import sys

def solution():
    n = int(sys.stdin.readline())
    graph = [[]] + [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

    dp = [0 for _ in range(n + 1)]

    for i in range(1, n + 1):
        dp[i] = max(dp[i], dp[i-1])
        if i + graph[i][0] - 1 <= n:
            dp[i + graph[i][0] - 1] = max(dp[i + graph[i][0] - 1], dp[i - 1] + graph[i][1])



    print(dp[-1])
solution()