import sys

from collections import deque



for test_case in range(1):
    l, w = map(int, sys.stdin.readline().split())
    graph = [sys.stdin.readline().rstrip() for _ in range(l)]

    def bfs(row, col):
        q = deque()

        direction = [[1, 0], [0, 1], [-1, 0], [0, -1]]
        visited = [[False for _ in range(w)] for _ in range(l)]
        visited[row][col] = True
        q.append((row, col, 0))
        ans = 0
        while q:
            cur_row, cur_col, cur_cnt = q.popleft()

            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]

                if 0 <= new_row < l and 0 <= new_col < w and graph[new_row][new_col] == 'L' and not visited[new_row][new_col]:
                    q.append((new_row, new_col, cur_cnt + 1))
                    visited[new_row][new_col] = True
                    ans = max(ans, cur_cnt + 1)
        return ans

    ans = 0
    for row in range(l):
        for col in range(w):
            if graph[row][col] == 'L':
                ans = max(ans, bfs(row, col))

    print(ans)