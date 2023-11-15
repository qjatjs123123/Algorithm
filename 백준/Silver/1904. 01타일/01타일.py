for test_case in range(1):
    n = int(input())
    dp = [1, 2] + [0 for i in range(n-2)]

    for i in range(2, n):
        dp[i] = (dp[i-2] + dp[i-1])%15746

    print(dp[n-1])