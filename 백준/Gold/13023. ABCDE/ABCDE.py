import sys
sys.setrecursionlimit(10**6)


def solution():
    n, m = map(int, sys.stdin.readline().split())
    graph = {}

    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())

        if a not in graph:
            graph[a] = [b]
        else:
            graph[a] += [b]

        if b not in graph:
            graph[b] = [a]
        else:
            graph[b] += [a]

    ans = False
    visited = [False for _ in range(n + 1)]

    def dfs(cur_node, cnt):
        nonlocal visited
        if cur_node not in graph:
            return 0
        if cnt == 4:
            return 0
        ans = 0
        for new_node in graph[cur_node]:
            if not visited[new_node]:

                visited[new_node] = True
                ans = max(dfs(new_node, cnt + 1)+1, ans)
                visited[new_node] = False

        return ans
    for i in range(n):
        visited[i] = True

        if dfs(i, 0) == 4:
            ans = True
            break
        visited[i] = False


    if ans:
        print(1)
    else:
        print(0)


solution()