import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    dp = [0 for i in range(1001)]
    dp[0], dp[1] = 1, 1

    for i in range(2, 1001):
        dp[i] = (dp[i-1] + dp[i-2]*2) % 10007

    print(dp[n])
