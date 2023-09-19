import sys
from collections import deque

import copy
# t = int(sys.stdin.readline())
input = sys.stdin.readline
for test_case in range(1):
    n, m, k = map(int, input().split())

    graph = [[0 for i in range(m)] for j in range(n)]
    visited = [[False for i in range(m)] for j in range(n) ]
    for _ in range(k):
        a, b = map(int, input().split())
        graph[a-1][b-1] = 1

    direction = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    def bfs(row, col):
        q = deque()
        q.append((row, col))
        ans = 0
        while q:
            cur_row, cur_col = q.popleft()
            ans += 1
            visited[cur_row][cur_col] = True
            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                if 0 <= new_row < n and 0 <= new_col < m and not visited[new_row][new_col] and graph[new_row][new_col] == 1:
                    q.append((new_row, new_col))
                    visited[new_row][new_col] = True

        return ans

    ans = 0
    for row in range(n):
        for col in range(m):
           if graph[row][col] == 1 and not visited[row][col]:
               ans = max(ans, bfs(row, col))

    print(ans)