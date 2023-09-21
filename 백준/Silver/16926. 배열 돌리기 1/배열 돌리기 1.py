import sys
for test_case in range(1):
    n, m, r = map(int, sys.stdin.readline().split())

    arr = []
    for _ in range(n):
        arr.append(list(map(int, sys.stdin.readline().split())))

    for depth in range(min(n,m) // 2):

        rotate = (m-2*depth)*2 + (n-2*depth)*2 - 4
        for _ in range(r % rotate):
            start_row, end_row, start_col, end_col = depth, n - depth - 1, depth, m - depth - 1
            tmp = arr[start_row][start_col]
            ## bottom
            for row in range(start_row, end_row):
                temp = arr[row + 1][start_col]
                arr[row + 1][start_col] = tmp
                tmp = temp

            ## right
            for col in range(start_col,end_col):
                temp = arr[end_row][col + 1]
                arr[end_row][col + 1] = tmp
                tmp = temp

            ## top
            for row in range(end_row, start_row, -1):
                temp = arr[row-1][end_col]
                arr[row - 1][end_col] = tmp
                tmp = temp


            ##left
            for col in range(end_col, start_col, -1):
                temp = arr[start_row][col - 1]
                arr[start_row][col - 1] = tmp
                tmp = temp

    for tmp in arr:
        print(' '.join(map(str, tmp)))
