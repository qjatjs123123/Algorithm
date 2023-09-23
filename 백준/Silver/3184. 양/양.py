
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
    for _ in range(n):
        tmp =sys.stdin.readline().split()
        graph.append(tmp[0])

    visited = [[False for _ in range(m)] for _ in range(n)]
    sheep, wolf = 0, 0
    direction =[[1, 0], [-1, 0], [0, 1], [0, -1]]
    def bfs(row, col):
        global  sheep, wolf
        q = deque()
        q.append((row, col))
        cur_sheep, cur_wolf = 0, 0
        visited[row][col] = True
        if graph[row][col] == 'v':
            cur_wolf += 1

        elif graph[row][col] == 'o':
            cur_sheep += 1
        while q:
            cur_row, cur_col = q.popleft()

            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                if 0 <= new_row < n and 0 <= new_col < m and not visited[new_row][new_col]:
                    if graph[new_row][new_col] == '#':
                        continue

                    if graph[new_row][new_col] == 'v':
                        cur_wolf += 1

                    elif graph[new_row][new_col] == 'o':
                        cur_sheep += 1

                    visited[new_row][new_col] = True
                    q.append((new_row, new_col))

        if cur_sheep > cur_wolf:
            sheep += cur_sheep
        else:
            wolf += cur_wolf


    for row in range(n):
        for col in range(m):
            if not visited[row][col] and graph[row][col] in ['.', 'v', 'o']:
                bfs(row, col)
    print(sheep,wolf)
