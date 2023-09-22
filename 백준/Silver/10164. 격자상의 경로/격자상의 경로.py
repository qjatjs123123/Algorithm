import sys
for test_case in range(1):
    n,m,k = map(int, sys.stdin.readline().split())

    graph = []
    cnt = 1
    row, col = 0, 0
    for r in range(n):
        tmp = []
        for c in range(m):
            tmp.append(cnt)
            if cnt == k:
                row, col = r, c
            cnt += 1
        graph.append(tmp)
    goal = []
    if row != 0 or col != 0:
        goal.append([row, col])
    goal.append([n-1, m - 1])

    start_row, start_col = 0, 0
    ans = 1
    for arr in goal:
        height, width = arr[0] - start_row, arr[1] - start_col
        new_arr = [[0 for _ in range(width + 2)] for _ in range(height + 2) ]
        new_arr[1][1] = 1
        for i in range(1, height + 2):
            for j in range(1, width + 2):
                if i== 1 and j == 1:
                    continue
                new_arr[i][j] = new_arr[i][j-1] + new_arr[i-1][j]

        ans = ans * new_arr[-1][-1]
        start_row, start_col = arr[0], arr[1]
    print(ans)