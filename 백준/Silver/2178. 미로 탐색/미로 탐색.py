from heapq import heappush, heappop
from collections import deque
from itertools import combinations, permutations
import math
import copy
import sys
for test_case in range(1):
    n, m = map(int, input().split())

    maps = []
    for _ in range(n):
        tmp = []
        for s in input():
            tmp.append(int(s))
        maps.append(tmp)

    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    ans = float("inf")
    visited = [[False for i in range(m)] for j in range(n)]
    visited[0][0] = True
    q = []
    heappush(q, (1, 0, 0))

    while q:
        cur_cnt, cur_row, cur_col = heappop(q)

        for direct in direction:
            new_row, new_col = cur_row + direct[0], cur_col + direct[1]
            if 0 <= new_row < n and 0 <= new_col < m and maps[new_row][new_col] == 1:
                maps[new_row][new_col] = maps[cur_row][cur_col] + 1
                heappush(q, (cur_cnt + 1, new_row, new_col))
    print(maps[n-1][m-1])