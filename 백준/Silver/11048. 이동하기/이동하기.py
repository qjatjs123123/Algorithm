import sys
sys.setrecursionlimit(10**6)
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    graph = []

    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    dp = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
    dp[1][1] = graph[0][0]

    for row in range(1, n + 1):
        for col in range(1, m + 1):

            dp[row][col] = max(
                               graph[row-1][col-1] + dp[row - 1][col],
                               graph[row-1][col-1] + dp[row - 1][col - 1],
                                graph[row - 1][col - 1] + dp[row][col - 1])

    print(dp[-1][-1])