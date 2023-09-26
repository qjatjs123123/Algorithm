from collections import deque
import copy
import sys
for test_case in range(1):
    n = int(input())
    maps = []
    max_height = 0
    for _ in range(n):
        tmp = list(map(int, input().split()))
        maps.append(tmp)
        max_height = max(max_height, max(tmp))

    def rain(height):
        new_maps = copy.deepcopy(maps)
        for row in range(n):
            for col in range(n):
                if new_maps[row][col] <= height:
                    new_maps[row][col] = -1

        return new_maps

    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    def bfs(row, col):

        q = deque()
        q.append((row, col))

        while q:
            cur_row, cur_col = q.popleft()
            if cur_row < 0 or cur_row == n or cur_col < 0 or cur_col == n or new_maps[cur_row][cur_col] == -1:
                continue

            new_maps[cur_row][cur_col] = -1
            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                q.append((new_row,new_col))

    ans = 0
    for height in range(max_height + 1):
        new_maps = rain(height)
        tmp = 0
        for row in range(n):
            for col in range(n):
                if new_maps[row][col] != -1:
                    bfs(row, col)
                    tmp += 1
        ans = max(ans, tmp)
    print(ans)