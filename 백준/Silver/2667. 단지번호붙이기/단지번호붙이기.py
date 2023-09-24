from collections import deque
import sys
for test_case in range(1):
    n = int(input())

    maps = []
    for _ in range(n):
        tmp = []
        for s in input():
            tmp.append(int(s))
        maps.append(tmp)

    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]

    def bfs(row, col):
        ans = 0

        q = deque()
        q.append((row, col))

        while q:
            cur_row, cur_col = q.popleft()

            if cur_row < 0 or cur_row == n or cur_col < 0 or cur_col == n or maps[cur_row][cur_col] != 1:
                continue
            maps[cur_row][cur_col] = -1
            ans += 1
            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                q.append((new_row, new_col))
        return ans

    cnt = 0
    ans = []
    for row in range(n):
        for col in range(n):
            if maps[row][col] == 1:
                ans.append(bfs(row, col))
                cnt += 1

    print(cnt)
    ans.sort()
    for i in ans:
        print(i)