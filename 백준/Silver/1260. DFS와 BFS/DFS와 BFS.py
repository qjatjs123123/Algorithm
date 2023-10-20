import sys
from collections import deque

import math
for test_case in range(1):
    n, m, v = map(int, sys.stdin.readline().split())
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

    visited = [False for i in range(n+1)]
    dfs_answer = []
    def dfs(v):
        dfs_answer.append(v)
        if v not in graph or visited[v]:
            return

        visited[v] = True
        direction = graph[v]
        direction.sort()

        for new_v in direction:
            if not visited[new_v]:
                dfs(new_v)
    dfs(v)
    visited = [False for i in range(n + 1)]
    bfs_answer = []

    def bfs(v):
        q = deque()
        q.append((v))
        visited[v] = True
        while q:
            cur_v = q.popleft()
            bfs_answer.append(cur_v)
            if cur_v not in graph:
                continue

            direction = graph[cur_v]
            direction.sort()
            for new_v in direction:
                if visited[new_v]:
                    continue
                visited[new_v] = True
                q.append((new_v))

    bfs(v)
    print(" ".join(map(str, dfs_answer)))
    print(" ".join(map(str, bfs_answer)))

