import sys

from collections import deque
for test_case in range(1):
    n = int(sys.stdin.readline())

    graph = []
    for _ in range(n):
        graph.append(list(sys.stdin.readline().rstrip()))


    q = deque()
    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    def bfs(row, col, color):

        q = deque()
        q.append((row, col))

        while q:
            cur_row, cur_col = q.popleft()

            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]

                if 0 <= new_row < n and 0 <= new_col < n and not visited[new_row][new_col] and graph[new_row][new_col] in color:
                    visited[new_row][new_col] = True
                    q.append((new_row, new_col))


    visited = [[False for i in range(n)] for j in range(n)]
    good, bad = 0, 0
    for row in range(n):
        for col in range(n):
            if not visited[row][col]:
                bfs(row, col, [graph[row][col]])
                visited[row][col] = True
                good += 1

    visited = [[False for i in range(n)] for j in range(n)]
    for row in range(n):
        for col in range(n):
            if not visited[row][col]:
                if graph[row][col] in ['R', 'G']:
                    bfs(row, col, ['R', 'G'])
                else:
                    bfs(row, col, ['B'])
                visited[row][col] = True
                bad += 1

    print(good, bad)