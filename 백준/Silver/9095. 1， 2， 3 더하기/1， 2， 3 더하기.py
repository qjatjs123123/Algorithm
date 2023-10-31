import sys

for test_case in range(1):
    n = int(sys.stdin.readline())

    dp = [0 for _ in range(12)]

    dp[0] = 1
    dp[1] = 1
    dp[2] = 2

    for i in range(3, 12):
        dp[i] = dp[i-1] + dp[i-2] + dp[i-3]

    for _ in range(n):
        num = int(sys.stdin.readline())
        print(dp[num])