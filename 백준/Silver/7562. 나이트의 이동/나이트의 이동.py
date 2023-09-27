from collections import deque
from itertools import combinations, permutations
import math
import copy
n = int(input())
for test_case in range(n):
    length = int(input())
    row, col = map(int, input().split())
    target_row, target_col = map(int, input().split())
    visitied = [[0 for i in range(length)] for j in range(length)]
    q = deque()
    q.append((row, col, 0))

    direction = [[1, -2], [2, -1], [2, 1], [1, 2], [-1, 2], [-2, 1], [-2, -1], [-1, -2]]
    while q:
        cur_row, cur_col, cur_cnt = q.popleft()

        if cur_row < 0 or cur_row >= length or cur_col < 0 or cur_col >= length or visitied[cur_row][cur_col] != 0:
            continue
        if cur_row == target_row and cur_col == target_col:
            print(cur_cnt)
            break
        visitied[cur_row][cur_col] = cur_cnt
        for direct in direction:
            new_row, new_col = cur_row + direct[0], cur_col + direct[1]
            q.append((new_row, new_col, cur_cnt + 1))
