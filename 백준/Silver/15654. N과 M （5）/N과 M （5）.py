import sys
import copy
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    ans = []
    tmp = []
    visited = [False for i in range(n)]
    def backtracking(l):
        if l == m:
            ans.append(copy.deepcopy(tmp))
            return

        for i in range(n):
            if not visited[i]:
                tmp.append(arr[i])
                visited[i] = True
                backtracking(l+1)
                tmp.pop()
                visited[i] = False

    backtracking(0)

    ans.sort()
    for i in ans:
        print(' '.join(map(str, i)))