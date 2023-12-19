import sys
sys.setrecursionlimit(10**6)

ans = False
def solution():
    n, m = map(int, sys.stdin.readline().split())
    g = [[] for _ in range(n)]
    for _ in range(m):
        a, b = map(int, sys.stdin.readline().split())
        g[a].append(b)
        g[b].append(a)



    visited = [False for _ in range(n + 1)]
    global ans
    def dfs(cur_node, cnt):
        nonlocal visited
        global ans
        if ans:
            return
        if cnt == 4:
            ans = True
            return
        for new_node in g[cur_node]:
            if not visited[new_node]:
                visited[new_node] = True
                dfs(new_node, cnt + 1)
                visited[new_node] = False

    for i in range(n):
        visited[i] = True
        dfs(i, 0)
        if ans:
            break
        visited[i] = False

    if ans:
        print(1)
    else:
        print(0)

solution()