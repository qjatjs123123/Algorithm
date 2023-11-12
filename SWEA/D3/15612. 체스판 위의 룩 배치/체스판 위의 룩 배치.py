T = int(input())
for test_case in range(1, T+1):
    graph = []

    for _ in range(8):
        graph.append(input())

    def checkRow(col):
        cnt = 0
        for r in range(8):
            if graph[r][col] == 'O':
                cnt += 1

        return cnt == 1

    def checkCol(row):
        cnt = 0
        for c in range(8):
            if graph[row][c] == 'O':
                cnt += 1

        return cnt == 1

    cnt = 0
    flg = True
    for row in range(8):
        for col in range(8):
            if graph[row][col] == 'O':
                cnt += 1
                if not(checkRow(col) and checkCol(row)):
                    flg = False
        if not flg:
            break

    if not flg or cnt != 8:
        print("#" + str(test_case) + " " + "no")

    else:
        print("#" + str(test_case) + " " + "yes")