T = int(input())
for test_case in range(1, T+1):
    row, col = map(int, input().split())
    graph = []

    for _ in range(row):
        graph.append(input())

    sharp = ''
    dot = ''
    for c in range(col):
        if c % 2 == 0:
            sharp += '#'
            dot += '.'
        else:
            sharp += '.'
            dot += '#'

    ans_sharp = []
    ans_dot = []
    cases = []
    for r in range(row):
        if r % 2 == 0:
            ans_sharp.append(sharp)
            ans_dot.append(dot)
        else:
            ans_sharp.append(dot)
            ans_dot.append(sharp)

    cases.append(ans_sharp)
    cases.append(ans_dot)
    flg = False
    for case in cases:
        ispossible = True
        for r in range(row):
            for c in range(col):
                if graph[r][c] == '?':
                    continue

                if graph[r][c] != case[r][c]:
                    ispossible = False

        if ispossible:
            print("#" + str(test_case) + " " + "possible")
            flg = True
            break

    if not flg:
        print("#" + str(test_case) + " " + "impossible")