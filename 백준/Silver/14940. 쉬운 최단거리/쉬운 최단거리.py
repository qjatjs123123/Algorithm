import sys
from heapq import heappush, heappop
from collections import deque
sys.setrecursionlimit(10**6)
import copy
import math
# t = int(sys.stdin.readline())
input = sys.stdin.readline
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    graph = []

    for row in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    goal_row, goal_col = 0, 0
    for row in range(n):
        flg = False
        for col in range(m):
            if graph[row][col] == 2:
                goal_row, goal_col = row, col
                flg = True
                break
        if flg:
            break

    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    graph[goal_row][goal_col] = 0
    q = deque()
    dp = {}
    for direct in direction:
        if  0 <= (goal_row + direct[0]) < n and 0 <= (goal_col + direct[1]) < m and graph[(goal_row + direct[0])][(goal_col + direct[1])] == 1:
            q.append((goal_row + direct[0], goal_col + direct[1], 1))
            dp[(goal_row + direct[0], goal_col + direct[1])] = True

    while q:
        cur_row, cur_col, cur_cnt = q.popleft()

        for direct in direction:
            new_row, new_col = cur_row + direct[0], cur_col + direct[1]

            if 0 <= new_row < n and 0 <= new_col < m and graph[new_row][new_col] == 1:
                if (new_row, new_col) in dp:
                    continue
                graph[new_row][new_col] = cur_cnt + 1
                q.append((new_row, new_col , cur_cnt + 1))

    for row in range(n):
        for col in range(m):
            if (row, col) in dp:
                continue
            if graph[row][col] == 1:
                graph[row][col] = -1

    for tmp in graph:
        print(' '.join(map(str, tmp)))
