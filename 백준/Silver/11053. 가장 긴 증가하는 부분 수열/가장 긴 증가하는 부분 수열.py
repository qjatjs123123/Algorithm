import sys
from collections import deque
input = sys.stdin.readline
from heapq import heappush,heappop
import math
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))

    dp = [0 for i in range(n)]
    dp[0] = 1

    for i in range(1, n):
        dp[i] = 1
        for j in range(i):
            if arr[i] > arr[j]:
                dp[i] = max(dp[i], dp[j] + 1)

    print(max(dp))