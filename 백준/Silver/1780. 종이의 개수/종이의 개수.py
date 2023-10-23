import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = []
    for _ in range(n):
        arr.append(sys.stdin.readline().split())

    one, zero, minus = 0, 0, 0
    def dfs(length, row, col):
        global one, zero, minus
        target = arr[row][col]
        flg = True
        for r in range(row, row + length):
            for c in range(col, col + length):
                if arr[r][c] != target:
                    flg = False
                    break
            if not flg:
                break

        if flg:
            if target == '0':
                zero += 1
            elif target == '1':
                one += 1
            else:
                minus += 1

        else:
            new_length = length // 3
            for r in range(row, row + length, new_length):
                for c in range(col, col + length, new_length):
                    dfs(new_length, r,c)

    dfs(n, 0, 0)
    print(minus)
    print(zero)
    print(one)