from collections import deque
for test_case in range(1):
    m, n, k = map(int, input().split())
    maps = [[0 for i in range(n)] for j in range(m)]

    for _ in range(k):
        x1, y1, x2, y2 = map(int, input().split())

        for y in range(y1, y2):
            for x in range(x1, x2):
                maps[y][x] = 1
    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    def bfs(row, col):

        q = deque()
        q.append((row, col))
        cnt = 0
        while q:
            cur_row, cur_col = q.popleft()

            if cur_row < 0 or cur_row == m or cur_col < 0 or cur_col == n or maps[cur_row][cur_col] == 1:
                continue
            maps[cur_row][cur_col] = 1
            cnt += 1
            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                q.append((new_row, new_col))

        return cnt
    ans = 0
    arr = []
    for row in range(m):
        for col in range(n):
            if maps[row][col] == 1:
                continue
            ans += 1
            arr.append(bfs(row, col))

    print(ans)
    arr.sort()
    print(*arr)