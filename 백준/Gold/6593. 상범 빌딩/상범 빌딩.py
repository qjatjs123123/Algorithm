import sys
from collections import deque


def solution():
    while True:
        l, r, c = map(int, sys.stdin.readline().split())
        if l == r == c == 0:
            break
        graph = []

        for _ in range(l):
            floor = []
            for _ in range(r):
                floor.append(list(sys.stdin.readline().rstrip()))
            sys.stdin.readline()
            graph.append(floor)

        start_l, start_r, start_c = 0, 0, 0

        for floor in range(l):
            flg = False
            for row in range(r):
                for col in range(c):
                    if graph[floor][row][col] == 'S':
                        start_l, start_r, start_c = floor, row, col
                        flg = True
                        break
                if flg:
                    break
            if flg:
                break

        direction = [[1, 0, 0], [-1, 0, 0], [0, 1, 0], [0, -1, 0], [0, 0, 1], [0, 0, -1]]
        q = deque()
        q.append((start_l, start_r, start_c, 0))
        flg = False
        ans = 0
        while q:
            cur_l, cur_r, cur_c, cur_cnt = q.popleft()

            for direct in direction:
                new_l, new_r, new_c = cur_l + direct[0], cur_r + direct[1], cur_c + direct[2]

                if 0 <= new_l < l and 0 <= new_r < r and 0 <= new_c < c and graph[new_l][new_r][new_c] in ['.', 'E']:
                    if graph[new_l][new_r][new_c] == 'E':
                        flg = True
                        ans = cur_cnt + 1
                        break
                    graph[new_l][new_r][new_c] = cur_cnt + 1
                    q.append((new_l, new_r, new_c, cur_cnt + 1))
            if flg:
                break

        if not flg:
            print('Trapped!')
        else:
            print('Escaped in ' + str(ans) + ' minute(s).')

solution()