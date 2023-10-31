import sys
from collections import deque

for test_case in range(1):
    n = int(sys.stdin.readline())

    arr = [0 for _ in range(n + 1)]

    q = deque()

    q.append((n, 0))

    while q:
        cur_num, cur_cnt = q.popleft()

        if cur_num == 1:
            print(cur_cnt)
            break

        if cur_num % 3 == 0 and arr[cur_num//3] == 0:
            arr[cur_num // 3] = cur_cnt + 1
            q.append((cur_num//3, cur_cnt + 1))

        if cur_num % 2 == 0 and arr[cur_num//2] == 0:
            arr[cur_num // 2] = cur_cnt + 1
            q.append((cur_num//2, cur_cnt + 1))

        if arr[cur_num - 1] == 0:
            arr[cur_num - 1] = cur_cnt + 1
            q.append((cur_num - 1, cur_cnt + 1))