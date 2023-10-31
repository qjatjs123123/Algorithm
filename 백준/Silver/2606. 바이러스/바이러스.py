import sys
from collections import deque

for test_case in range(1):
    n = int(sys.stdin.readline())
    cnt = int(sys.stdin.readline())

    visited = [False for _ in range(n + 1)]
    dp = {}

    for _ in range(cnt):
        a, b = map(int, sys.stdin.readline().split())

        if a not in dp:
            dp[a] = [b]
        else:
            dp[a] += [b]

        if b not in dp:
            dp[b] = [a]
        else:
            dp[b] += [a]

    visited[1] = True

    q = deque()
    q.append((1))

    ans = 0
    while q:
        cur_n = q.popleft()

        if cur_n not in dp:
            continue

        for new_n in dp[cur_n]:
            if not visited[new_n]:
                visited[new_n] = True
                q.append((new_n))
                ans += 1
    print(ans)