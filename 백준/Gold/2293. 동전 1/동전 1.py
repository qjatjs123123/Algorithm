import sys
for test_case in range(1):
    n, k = map(int, sys.stdin.readline().split())
    coins = []

    for _ in range(n):
        coins.append(int(sys.stdin.readline()))
    coins.sort()

    dp = [0 for i in range(k + 1)]
    dp[0] = 1

    for coin in coins:
        for i in range(1, k + 1):
            if i - coin >= 0:
                dp[i]= dp[i-coin] + dp[i]

    print(dp[k])