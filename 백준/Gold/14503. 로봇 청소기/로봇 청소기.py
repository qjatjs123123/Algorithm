import sys

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    r, c, d = map(int, sys.stdin.readline().split())
    graph = []
    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    direction = [[-1, 0], [0, 1], [1, 0], [0, -1]] # 북동남서
    ans = 0

    while True:
        if graph[r][c] == 0:
            graph[r][c] = 2
            ans += 1

        ## 주변 4칸 청소되어 있는지 확인
        isClean = True
        for direct in direction:
            new_row, new_col = r + direct[0], c + direct[1]

            if 0 <= new_row < n and 0 <= new_col < m and graph[new_row][new_col] == 0:
                isClean = False
                break

        if isClean:
            direct = direction[d]
            new_row = r - direct[0]
            new_col = c - direct[1]
            ## 뒤쪽 칸이 벽일 때
            if new_row == n or new_col == m or graph[new_row][new_col] == 1:
                break

            ## 뒤로 후진
            direct = direction[d]
            r = r - direct[0]
            c = c - direct[1]

        else:
            for _ in range(4):
                if d == 0:
                    d = 3
                else:
                    d -= 1

                new_row, new_col = r + direction[d][0], c + direction[d][1]
                if 0 <= new_row < n and 0<= new_col < m and graph[new_row][new_col] == 0:
                    r = new_row
                    c = new_col
                    break

    print(ans)