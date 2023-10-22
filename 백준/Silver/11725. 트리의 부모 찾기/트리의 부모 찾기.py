import sys
from collections import deque
input = sys.stdin.readline
from heapq import heappush, heappop
from itertools import combinations
import math

for test_case in range(1):
    n = int(sys.stdin.readline())

    dp = {}
    for _ in range(n-1):
        x, y = map(int, sys.stdin.readline().split())
        if x not in dp:
            dp[x] = [y]
        else:
            dp[x] += [y]

        if y not in dp:
            dp[y] =[x]
        else:
            dp[y] += [x]

    visited = [False for _ in range(n+1)]
    q= deque()
    q.append((1))
    visited[1] = True
    ans = {}
    while q:
        cur_n = q.popleft()
        if cur_n not in dp:
            continue
        direction = dp[cur_n]

        for new_n in direction:
            if visited[new_n]:
                continue
            ans[new_n] = cur_n
            visited[new_n] = True
            q.append((new_n))

    for i in range(2, n+1):
        print(ans[i])