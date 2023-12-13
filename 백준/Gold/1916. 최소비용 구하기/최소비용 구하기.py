import sys

from heapq import heappush, heappop

for test_case in range(1):
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())

    graph = [[] for _ in range(n + 1)]

    for _ in range(m):
        start, end, cost = map(int, sys.stdin.readline().split())
        graph[start].append((end, cost))

    distance = [float('inf') for _ in range(n + 1)]
    visited = [False for i in range(n + 1)]

    def dijkstra(start):
        q = []

        heappush(q, (0, start))
        distance[start] = 0

        while q:
            dist, node = heappop(q)
            visited[node] = True

            if distance[node] < dist:
                continue

            for new_node, cost in graph[node]:
                if visited[new_node]:
                    continue

                if distance[new_node] > dist + cost:
                    heappush(q, (dist + cost, new_node))
                    distance[new_node] = dist + cost


    start, end = map(int, sys.stdin.readline().split())
    dijkstra(start)
    print(distance[end])