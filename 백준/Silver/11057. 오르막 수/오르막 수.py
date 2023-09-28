for test_case in range(1):
    n = int(input())
    dp = [1 for i in range(10)]

    for i in range(1, n):
        for j in range(10):
            dp[j] = sum(dp[j:])

    print(sum(dp) % 10007)