T = int(input())
for test_case in range(1, T+1):
    n = int(input())
    graph = []

    for _ in range(n):
        graph.append(input())

    sharp_cnt = 0
    for row in range(n):
        for col in range(n):
            if graph[row][col] == '#':
                sharp_cnt += 1

    row, col = 0, 0
    for r in range(n):
        flg = False
        for c in range(n):
            if graph[r][c] == '#':
                row = r
                col = c
                flg = True
                break
        if flg:
            break

    side = 0

    for c in range(col, n):
        if graph[row][c] == '#':
            side += 1
        else:
            break

    if sharp_cnt != side*side:
        print("#" + str(test_case) + " " + "no")
    else:
        flg = False
        for r in range(row, row+side):
            ans = graph[r][col:col+side]
            if "." in ans:
                print("#" + str(test_case) + " " + "no")
                flg = True
                break

        if not flg:
            print("#" + str(test_case) + " " + "yes")
