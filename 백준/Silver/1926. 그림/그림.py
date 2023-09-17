import sys
from collections import deque

# t = int(sys.stdin.readline())

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    graph = []

    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    visited = [[False for i in range(m)] for j in range(n)]

    direction = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    def bfs(row, col):
        q = deque()
        q.append((row, col))
        ans = 0
        while q:
            cur_row, cur_col = q.popleft()

            if cur_row < 0 or cur_row == n or cur_col < 0 or cur_col == m or visited[cur_row][cur_col] or graph[cur_row][cur_col] == 0:
                continue
            visited[cur_row][cur_col] = True
            ans += 1
            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                q.append((new_row, new_col))

        return ans

    max_rect = 0
    cnt = 0
    for row in range(n):
        for col in range(m):
            if visited[row][col] or graph[row][col] == 0:
                continue
            cnt += 1
            max_rect = max(max_rect, bfs(row, col))

    print(cnt)
    print(max_rect)