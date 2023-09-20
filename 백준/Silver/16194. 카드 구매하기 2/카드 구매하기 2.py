import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))

    dp = [0] * n
    dp[0] = arr[0]

    for i in range(1, n):
        dp[i] = arr[i]
        for j in range(i-1, -1, -1):
            dp[i] = min(dp[i], dp[j] + dp[i-j-1])

    print(dp[n-1])