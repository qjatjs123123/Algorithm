from collections import deque
from itertools import combinations, permutations
import math
import copy
import sys
for test_case in range(1):
    n, k = map(int, input().split())

    q= deque()
    q.append((n, 0))
    dp = {}
    while q:
        pos_n, cnt = q.popleft()
        if pos_n == k:
            print(cnt)
            break
        if pos_n < 0 or pos_n > 100000:
            continue
        if pos_n not in dp:
            q.append((pos_n - 1, cnt + 1))
            q.append((pos_n + 1, cnt + 1))
            q.append((2 * pos_n, cnt + 1))

            dp[pos_n] = True
