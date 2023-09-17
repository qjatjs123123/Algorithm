import sys
from collections import deque

# t = int(sys.stdin.readline())

for test_case in range(1):
    f, s, g, u, d = map(int, sys.stdin.readline().split())

    q = deque()
    q.append((s, 0))
    dp = {}
    flg = False
    while q:
        cur_s, cur_cnt = q.popleft()
        if cur_s in dp:
            continue
        if cur_s == g:
            print(cur_cnt)
            flg = True
            break
        if cur_s + u <= f:
            q.append((cur_s + u, cur_cnt + 1))
        if cur_s - d >= 1:
            q.append((cur_s - d, cur_cnt + 1))
        dp[cur_s] = True
    if not flg:
        print("use the stairs")

