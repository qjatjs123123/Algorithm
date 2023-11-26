import sys

for test_case in range(1):
    n = int(sys.stdin.readline())
    ans = [[' ' for i in range(n)] for j in range(n)]

    def recursion(startRow, startCol, endRow, endCol, l):
        if l == 1:
            return

        cnt = 0
        for row in range(startRow, endRow, l // 3):
            for col in range(startCol, endCol, l // 3):
                if cnt != 4:
                    ans[row][col] = '*'
                    recursion(row, col, row + l // 3, col + l // 3, l//3)
                cnt += 1

    recursion(0,0, n, n, n)
    for i in ans:
        print(''.join(i))