import sys
n = int(sys.stdin.readline())
for test_case in range(n):
    n1 = int(sys.stdin.readline())
    arr = []
    arr.append([0] + list(map(int, sys.stdin.readline().split())))
    arr.append([0] + list(map(int, sys.stdin.readline().split())))
    dp = [[0 for i in range(n1 + 1)] for j in range(2)]

    for i in range(1, n1 + 1):
        if i == 1:
            dp[0][i] = arr[0][i]
            dp[1][i] = arr[1][i]
            continue
        dp[0][i] = max(dp[1][i-1] + arr[0][i], max(dp[0][i-2], dp[1][i-2]) + arr[0][i])
        dp[1][i] = max(dp[0][i-1] + arr[1][i], max(dp[0][i-2], dp[1][i-2]) + arr[1][i])

    print(max(dp[0][n1], dp[1][n1]))