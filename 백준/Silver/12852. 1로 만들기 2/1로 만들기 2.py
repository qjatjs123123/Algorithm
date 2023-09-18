import sys
from collections import deque
import copy
# t = int(sys.stdin.readline())

for test_case in range(1):
    n = int(sys.stdin.readline())

    dp = [0 for i in range(n + 1)]
    dp[1] = [0, [1]]
    for i in range(2, n + 1):
        n1, n2, n3 = [dp[i - 1][0], i-1], [float("inf"), float("inf")], [float("inf"), float("inf")]
        if i % 2 == 0:
            n2 = [dp[i // 2][0], i//2]
        if i % 3 == 0:
            n3 = [dp[i // 3][0], i//3]

        arr = [n1, n2, n3]
        arr.sort()


        dp[i] = [arr[0][0] + 1, arr[0][1]]


    print(dp[n][0])
    ans = str(n)

    while True:
        if n == 1:
            break
        ans += " " + str(dp[n][1])
        n = dp[n][1]

    print(ans)