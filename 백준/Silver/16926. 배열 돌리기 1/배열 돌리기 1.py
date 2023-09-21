import sys
from collections import deque

# t = int(sys.stdin.readline())
input = sys.stdin.readline
for test_case in range(1):
    n, m, r = map(int, sys.stdin.readline().split())

    arr = []
    for _ in range(n):
        arr.append(list(map(int, sys.stdin.readline().split())))
    s = 0
    for depth in range(min(n,m) // 2):
        q = deque()
        start_row, end_row, start_col, end_col = depth, n - depth - 1, depth, m - depth - 1
        for row in range(start_row, end_row):
            q.append(arr[row][start_col])

        for col in range(start_col, end_col):
            q.append(arr[end_row][col])

        for row in range(end_row,start_row,-1):
            q.append(arr[row][end_col])

        for col in range(end_col, start_col, -1):
            q.append(arr[start_row][col])

        cnt = (n - 2*depth)*2 +  (m - 2*depth)*2 - 4

        q.rotate(r % cnt)

        for row in range(start_row, end_row):
            arr[row][start_col] = q.popleft()

        for col in range(start_col, end_col):
            arr[end_row][col] = q.popleft()

        for row in range(end_row,start_row,-1):
            arr[row][end_col] = q.popleft()

        for col in range(end_col, start_col, -1):
            arr[start_row][col] = q.popleft()

    for tmp in arr:
        print(' '.join(map(str, tmp)))