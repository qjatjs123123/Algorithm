import sys
import math
for test_case in range(1):
    n = int(sys.stdin.readline())
    dp = [0 for _ in range(n + 1)]
    dp[1] = 1

    for i in range(2, n + 1):
        dp[i] = i
        for j in range(1, int(math.sqrt(i)) + 1):
            dp[i] = min(dp[i], 1 + dp[i - (j*j)])

    print(dp[n])