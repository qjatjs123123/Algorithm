import sys
from collections import deque

for test_case in range(1):
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())

    dp = {}

    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())

        if a not in dp:
            dp[a] = [b]
        else:
            dp[a] += [b]

        if b not in dp:
            dp[b] = [a]
        else:
            dp[b] += [a]

    visited = [False for _ in range(n + 1)]

    q = deque()

    q.append((1, 0))
    ans = 0
    visited[1] = True
    while q:
        cur_n, cur_cnt = q.popleft()

        if cur_n not in dp or cur_cnt == 2:
            continue

        direction = dp[cur_n]

        for new_n in direction:
            if not visited[new_n]:
                visited[new_n] = True
                q.append((new_n, cur_cnt + 1))
                ans += 1

    print(ans)