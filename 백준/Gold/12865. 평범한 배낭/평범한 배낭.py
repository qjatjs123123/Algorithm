import sys

for test_case in range(1):
    n, k = map(int, sys.stdin.readline().split())

    arr = []
    for _ in range(n):
        arr.append(list(map(int, sys.stdin.readline().split())))

    dp = [[0 for i in range(k + 1)] for j in range(n + 1)]

    for i in range(1, n + 1):
        for j in range(1, k + 1):
            cur_weight, cur_value = arr[i-1][0], arr[i-1][1]

            if cur_weight <= j:
                dp[i][j] = max(dp[i-1][j - cur_weight] + cur_value, dp[i-1][j])
            else:
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])

    print(dp[n][k])