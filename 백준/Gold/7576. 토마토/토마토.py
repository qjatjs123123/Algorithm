import sys
from collections import deque


def solution():
    m, n = map(int, sys.stdin.readline().split())

    graph = []
    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    q = deque()
    for row in range(n):
        for col in range(m):
            if graph[row][col] == 1:
                q.append((row, col, 1))

    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    ans = 0
    while q:
        cur_row, cur_col, cur_cnt = q.popleft()

        for direct in direction:
            new_row, new_col = cur_row + direct[0], cur_col + direct[1]

            if 0 <= new_row < n and 0 <= new_col < m and graph[new_row][new_col] == 0:
                graph[new_row][new_col] = cur_cnt + 1
                q.append((new_row, new_col, cur_cnt + 1))
                ans = cur_cnt
    flg = False
    for row in range(n):
        for col in range(m):
            if graph[row][col] == 0:
                flg = True
                break
        if flg:
            break
    if flg:
        print(-1)
    else:
        print(ans)
solution()