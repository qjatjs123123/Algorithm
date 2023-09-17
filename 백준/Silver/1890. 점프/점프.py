import sys
from collections import deque


# t = int(sys.stdin.readline())

for test_case in range(1):
    n = int(sys.stdin.readline())
    graph = []
    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    dp = {}
    def dfs(row, col):
        if row >= n or col >= n or (row != n-1 and col != n-1 and graph[row][col] == 0):
            return 0
        distance = graph[row][col]
        num = 0
        if (row, col) in dp:
            return dp[(row, col)]

        if graph[row][col] == 0:
            return 1

        ## right jump
        num = dfs(row, col + distance) + dfs(row + distance, col)

        dp[(row, col)] = num
        return num
    print(dfs(0, 0))

