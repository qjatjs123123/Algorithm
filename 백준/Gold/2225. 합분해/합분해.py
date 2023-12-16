import sys

for test_case in range(1):
    n, k = map(int, sys.stdin.readline().split())
    dp = [[0 for i in range(n + 1)] for j in range(k + 1)]

    for i in range(1, k + 1):
        for j in range(n + 1):
            if i == 1:
                dp[i][j] = 1
                continue

            dp[i][j] = sum(dp[i-1][:j+1]) % 1000000000

    print(dp[k][n])