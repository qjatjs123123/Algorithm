import sys
from itertools import combinations
for test_case in range(1):
    n = int(sys.stdin.readline())

    for _ in range(n):
        cnt = int(sys.stdin.readline())
        dp = {}

        for _ in range(cnt):
            name, type = sys.stdin.readline().split()

            if type not in dp:
                dp[type] = [name]
            else:
                dp[type] += [name]

        types = list(dp.keys())
        arr = []
        for type in types:
            arr.append(len(dp[type]))

        ans = 1

        for num in arr:
            ans *= num + 1
        print(ans - 1)