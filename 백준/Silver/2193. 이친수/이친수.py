import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    dp = [0 for i in range(91)]
    dp[1], dp[2] = 1, 1

    for i in range(3, n+1):
        dp[i] = dp[i-1] + dp[i-2]
    print(dp[n])