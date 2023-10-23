import sys
from collections import deque

import math

for test_case in range(1):
    n = int(sys.stdin.readline())

    arr = list(map(int, sys.stdin.readline().split()))
    dp = [0 for _ in range(len(arr))]
    dp[0] = arr[0]
    ans = dp[0]
    for i in range(1, len(arr)):
        dp[i] = arr[i]
        for j in range(i, -1, -1):
            if arr[i] > arr[j]:
                dp[i] = max(dp[i], arr[i] + dp[j])

    print(max(dp))
