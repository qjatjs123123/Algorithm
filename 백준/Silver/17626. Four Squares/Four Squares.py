import sys
import math
def solution():
    for test_case in range(1):
        n = int(sys.stdin.readline())

        dp = [0, 1]

        for num in range(2, n + 1):
            target=4
            if math.sqrt(num) % 1 == 0:
                target = 1
                dp.append(target)
                continue
            for i in range(1, int(math.sqrt(num)) + 1):
                target = min(target, 1 + dp[num - i**2])
                if target == 2:
                    break
            dp.append(target)

        print(dp[n])

solution()