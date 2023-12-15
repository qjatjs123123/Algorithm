import sys
from collections import deque

for test_case in range(1):
    n, k = map(int, sys.stdin.readline().split())

    dp = [float("inf") for i in range(100001)]

    q = deque()

    q.append((n, 0))
    dp[n] = 0
    while q:
        cur_pos, cur_cnt = q.popleft()

        cases = [(cur_pos - 1, 1), (cur_pos + 1, 1), (cur_pos * 2, 0)]
        if dp[cur_pos] < cur_cnt:
            continue

        if cur_pos == k:
            print(cur_cnt)
            break

        for new_pos, time in cases:
            if 0 <= new_pos <= 100000 and dp[new_pos] > cur_cnt + time:
                dp[new_pos] = cur_cnt + time
                q.append((new_pos, cur_cnt + time))