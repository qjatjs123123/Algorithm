import sys
from collections import deque
input = sys.stdin.readline
from heapq import heappush, heappop
import math
for test_case in range(1):
    n = int(sys.stdin.readline())
    num = list(map(int, sys.stdin.readline().split()))

    arr = list(set(num))
    arr.sort()
    dp = {}
    for i in range(len(arr)):
        dp[arr[i]] = i

    ans = []
    for i in num:
        ans.append(dp[i])

    print(' '.join(map(str,ans)))