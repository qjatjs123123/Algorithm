import sys
from collections import deque
input = sys.stdin.readline
from heapq import heappush,heappop
import math
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    graph = {}

    for _ in range(m):
        x, y = map(int, sys.stdin.readline().split())
        if x in graph:
            graph[x] += [y]
        else:
            graph[x] = [y]

        if y in graph:
            graph[y] += [x]
        else:
            graph[y] = [x]

    visited = [False for _ in range(n+1)]

    def bfs(v):
        q = deque()
        q.append((v))
        visited[v] = True

        while q:
            cur_v = q.popleft()

            if cur_v not in graph:
                continue

            direction = graph[cur_v]
            for new_v in direction:
                if not visited[new_v]:
                    visited[new_v] = True
                    q.append((new_v))

    ans = 0
    for v in range(1, n + 1):
        if not visited[v]:
            bfs(v)
            ans += 1

    print(ans)