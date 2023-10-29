import sys
for test_case in range(1):
    t = int(sys.stdin.readline())
    dp = [0 for _ in range(10000001)]
    dp[1] = 1
    dp[0] = 1
    for i in range(2, 10000001):
        if dp[i] != 0:
            continue
        dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009
    for _ in range(t):
        n = int(sys.stdin.readline())

        print(dp[n])