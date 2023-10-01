for test_case in range(1):
    n, m = map(int, input().split())
    arr = []
    ans = []
    for _ in range(n):
        tmp = []
        for num in input():
            tmp.append(int(num))
        arr.append(tmp)
    for _ in range(n):
        tmp = []
        for num in input():
            tmp.append(int(num))
        ans.append(tmp)
    cnt = 0
    for row in range(n-2):
        for col in range(m-2):
            if arr[row][col] != ans[row][col]:
                for r in range(row, row + 3):
                    for c in range(col, col + 3):
                        if arr[r][c] == 0:
                            arr[r][c] = 1
                        else:
                            arr[r][c] = 0
                cnt += 1

    flg = False
    for row in range(n):
        for col in range(m):
            if arr[row][col] != ans[row][col]:
                flg = True
                break
        if flg:
            break

    if not flg:
        print(cnt)
    else:
        print(-1)