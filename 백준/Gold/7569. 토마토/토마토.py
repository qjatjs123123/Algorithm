import sys
from collections import deque
for test_case in range(1):
    m, n, h = map(int, sys.stdin.readline().split())
    graph = []
    tmp = []
    for i in range(n * h):
        if i % n == 0 and i != 0:
            graph.append(tmp)
            tmp = []

        tmp.append(list(map(int, sys.stdin.readline().split())))
    graph.append(tmp)

    direction = [[0, 1, 0], [0, 0, 1], [0, -1, 0], [0, 0, -1], [1, 0, 0], [-1, 0, 0]]

    q = deque()

    for z in range(h):
        for row in range(n):
            for col in range(m):
                if graph[z][row][col] == 1:
                    q.append((z, row, col, 1))

    while q:
        cur_z, cur_row, cur_col, cur_cnt = q.popleft()

        for new_direct in direction:
            new_z, new_row, new_col = cur_z + new_direct[0], cur_row + new_direct[1], cur_col + new_direct[2]

            if 0 <= new_z < h and 0 <= new_row < n and 0 <= new_col < m and graph[new_z][new_row][new_col] == 0:
                graph[new_z][new_row][new_col] = cur_cnt + 1
                q.append((new_z, new_row, new_col, cur_cnt + 1))


    ans = 0
    flg = True
    for z in range(h):
        for row in range(n):
            for col in range(m):
                if graph[z][row][col] == 0:
                    ans = 0
                    flg = False
                    break
                ans = max(ans, graph[z][row][col])
            if not flg:
                break
        if not flg:
            break

    print(ans - 1)