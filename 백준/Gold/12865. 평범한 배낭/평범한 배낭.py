import sys

def solution():
    n, k = map(int, sys.stdin.readline().split())

    dp = [[0 for _ in range(k + 1)] for _ in range(n + 1)]
    graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

    for i in range(1, n + 1):
        w, v = graph[i-1]
        for j in range(1, k + 1):
            if j < w:
                dp[i][j] = dp[i-1][j]
            else:
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-w] + v)

    print(dp[n][k])

solution()