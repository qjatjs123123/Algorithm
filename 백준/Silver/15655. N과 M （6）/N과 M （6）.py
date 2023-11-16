import sys

import copy

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))

    ans = []
    visited = [False for i in range(n)]
    tmp = []

    def backtracking(l):
        if l == m:
            a = copy.deepcopy(tmp)
            ans.append(a)
            return

        for i in range(n):
            if not tmp:
                tmp.append(arr[i])
                visited[i] = True
                backtracking(l + 1)
                visited[i] = False
                tmp.pop()
                continue

            if not visited[i] and tmp[-1] <= arr[i]:
                tmp.append(arr[i])
                visited[i] = True
                backtracking(l + 1)
                visited[i] = False
                tmp.pop()

    backtracking(0)
    ans.sort()

    for i in ans:
        print(' '.join(map(str, i)))