import sys

from collections import deque
for test_case in range(1):
    m, n = map(int, sys.stdin.readline().split())
    graph = []

    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    q = deque()

    for row in range(n):
        for col in range(m):
            if graph[row][col] == 1:
                q.append((row, col, 0))

    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    while q:
        cur_row, cur_col, cur_cnt = q.popleft()

        for direct in direction:
            new_row, new_col = cur_row + direct[0], cur_col + direct[1]

            if 0 <= new_row < n and 0 <= new_col < m and graph[new_row][new_col] == 0:
                graph[new_row][new_col] = cur_cnt + 1
                q.append((new_row, new_col, cur_cnt + 1))

    ans = 0

    for row in range(n):
        flg = False
        for col in range(m):
            if graph[row][col] == 0:
                flg = True
                ans = -1
                break
            ans = max(ans, graph[row][col])
        if flg:
            break
    if ans == 1:
        ans = 0
    print(ans)