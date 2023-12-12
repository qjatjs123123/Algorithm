import sys

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    graph = []
    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    house, chicken = [], []
    for row in range(n):
        for col in range(n):
            if graph[row][col] == 1:
                house.append((row, col))
            if graph[row][col] == 2:
                chicken.append((row, col))

    cases = []
    ans = 99999
    def backtracking(l):
        global ans
        if len(cases) == m:
            total = 0
            for hou in house:
                dist = 99999
                for case in cases:
                    dist = min(dist, abs(hou[0] - case[0]) + abs(hou[1] - case[1]))
                total += dist
            ans = min(ans, total)
            return

        for i in range(l, len(chicken)):
            cases.append(chicken[i])
            backtracking(i + 1)
            cases.pop()

    backtracking(0)
    print(ans)