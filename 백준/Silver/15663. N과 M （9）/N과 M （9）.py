import sys
import copy

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))

    visited = [False for _ in range(n)]
    dp = {}
    ans = []
    def dfs(tmp):

        if len(tmp) == m:
            if tuple(tmp) in dp:
                return
            else:
                dp[tuple(tmp)] = True
                ans.append(copy.deepcopy(tmp))
                return

        for i in range(n):
            if not visited[i]:
                visited[i] = True
                tmp.append(arr[i])
                dfs(tmp)
                visited[i] = False
                tmp.pop()
    dfs([])
    ans.sort()

    for a in ans:
        print(' '.join(map(str, a)))