import sys
import math
def solution():
    for test_case in range(1):
        n = int(sys.stdin.readline())

        dp = [float("inf") for i in range(n + 1)]
        dp[0], dp[1] = 0, 1

        for num in range(2, n + 1):
            if math.sqrt(num) % 1 == 0:
                dp[num] = 1
                continue
            for i in range(1, int(math.sqrt(n)) + 1):     
                dp[num] = min(dp[num], 1 + dp[num - i**2])
                if dp[num] == 2:
                    break

        print(dp[n])

solution()