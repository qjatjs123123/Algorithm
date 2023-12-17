import sys

for test_case in range(1):
    n = int(sys.stdin.readline())
    graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

    #방향 0 => 오른쪽 1 => 아래쪽, 2=> 대각선


    dp = [[[0 for _ in range(n)] for _ in range(n)] for i in range(3)]
    dp[0][0][1] = 1 # 처음 위치 초기화
    for j in range(2, n):
        if graph[0][j] == 0:
            dp[0][0][j] = dp[0][0][j-1]


    for i in range(1, n):
        for j in range(1, n):
            if graph[i][j] == 0 and graph[i - 1][j] == 0 and graph[i][j - 1] == 0:
                dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1]
            if graph[i][j] == 0:
                dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1]  # 가로
                dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j]  # 세로

    print(sum([dp[i][n - 1][n - 1] for i in range(3)]))
