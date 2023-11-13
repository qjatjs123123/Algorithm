import sys
for test_case in range(1):
    n = int(sys.stdin.readline())

    graph = [0]

    for _ in range(n):
        graph.append(int(sys.stdin.readline()))

    dp = [[0,0] for _ in range(n + 1)]

    dp[1] = [graph[1], 0]

    for i in range(2, n+1):
        dp[i][0] = max(dp[i-2]) + graph[i]
        dp[i][1] = dp[i-1][0] + graph[i]

    print(max(dp[-1]))