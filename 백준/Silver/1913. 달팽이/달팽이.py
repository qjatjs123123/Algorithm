import sys
for test_case in range(1):
    n = int(sys.stdin.readline().rstrip())
    m = int(sys.stdin.readline().rstrip())

    graph = [[0 for i in range(n)] for j in range(n)]

    num = n*n
    length = n // 2
    cur_col, cur_row = 0, 0
    for i in range(length+1):
        if i != 0:
            cur_row += 1
        for row in range(cur_row, n):
            if graph[row][cur_col] == 0:
                graph[row][cur_col] = num
                cur_row = row
                num -= 1
            else:
                break

        for col in range(cur_col+1, n):
            if graph[cur_row][col] == 0:
                graph[cur_row][col] = num
                cur_col = col
                num -= 1
            else:
                break

        for row in range(cur_row -1, -1, -1):
            if graph[row][cur_col] == 0:
                graph[row][cur_col] = num
                cur_row = row
                num -= 1
            else:
                break

        for col in range(cur_col - 1, -1, -1):
            if graph[cur_row][col] == 0:
                graph[cur_row][col] = num
                cur_col = col
                num -= 1
            else:
                break

    for g in graph:
        print(' '.join(map(str, g)))

    for row in range(n):
        for col in range(n):
            if graph[row][col] == m:
                print(row+1, col+1)
                break