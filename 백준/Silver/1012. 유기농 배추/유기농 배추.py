import sys
from collections import deque
from heapq import heappush,heappop
import math
tc = int(sys.stdin.readline())
for test_case in range(tc):
    m,n,k = map(int, sys.stdin.readline().split())
    graph = [[0 for _ in range(m)] for _ in range(n)]
    for _ in range(k):
        col, row = map(int, sys.stdin.readline().split())
        graph[row][col] = 1

    visited = [[False for i in range(m)] for j in range(n)]
    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    def bfs(row, col):
        q = deque()
        q.append((row, col))

        while q:
            cur_row, cur_col = q.popleft()

            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                if 0<= new_row<n and 0<= new_col<m and graph[new_row][new_col] == 1 and not visited[new_row][new_col]:
                    visited[new_row][new_col] = True
                    q.append((new_row, new_col))
    ans = 0
    for row in range(n):
        for col in range(m):
            if graph[row][col] == 1 and not visited[row][col]:
                ans += 1
                bfs(row, col)

    print(ans)