import sys
from collections import deque

for test_case in range(1):
    n, m, k, x = map(int, sys.stdin.readline().split())

    dp = {}
    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())

        if a not in dp:
            dp[a] = [b]
        else:
            dp[a] += [b]

    visited = [0 for i in range(n+1)]
    visited[x] = -1
    q = deque()
    q.append((x, 0))
    ans = []
    while q:
        cur_node, cur_distance = q.popleft()

        if cur_node not in dp:
            continue

        direction = dp[cur_node]

        for new_node in direction:
            if visited[new_node] == 0:
                visited[new_node] = cur_distance + 1
                if visited[new_node] == k:
                    ans.append(new_node)
                    continue
                q.append((new_node, cur_distance + 1))
    ans.sort()
    if not ans:
        print(-1)
    else:
        for a in ans:
            print(a)