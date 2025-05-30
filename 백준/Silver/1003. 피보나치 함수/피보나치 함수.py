import sys

for test_case in range(1):
    n = int(sys.stdin.readline())

    dp = [[0, 0] for _ in range(41)]
    dp[0] = [1, 0]
    dp[1] = [0, 1]
    dp[2] = [1, 1]

    for i in range(3, 41):
        dp[i] = [dp[i-1][0] + dp[i-2][0], dp[i-1][1] + dp[i-2][1]]

    for _ in range(n):
        num = int(sys.stdin.readline())
        print(' '.join(map(str, dp[num])))