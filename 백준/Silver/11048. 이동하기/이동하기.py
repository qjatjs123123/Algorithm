import sys
sys.setrecursionlimit(10**6)
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    graph = []

    for _ in range(n):
        graph.append(list(map(int, sys.stdin.readline().split())))

    dp = {}
    direction = [[1, 0], [0, 1]]
    def dfs(row, col):

        if (row, col) in dp:
            return dp[(row, col)]

        num = 0
        for direct in direction:
            new_row, new_col = row + direct[0], col + direct[1]

            if 0 <= new_row < n and 0 <= new_col < m:
                num = max(num, dfs(new_row, new_col) + graph[new_row][new_col])

        dp[(row, col)] = num
        return num

    print(dfs(0, 0) + graph[0][0])