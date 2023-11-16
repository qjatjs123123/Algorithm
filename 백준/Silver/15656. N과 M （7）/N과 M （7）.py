import sys
import copy

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))

    ans = []
    tmp = []

    def backtracking(l):
        if l == m:
            a = copy.deepcopy(tmp)
            ans.append(a)
            return

        for i in range(n):
            tmp.append(arr[i])
            backtracking(l + 1)
            tmp.pop()

    backtracking(0)
    ans.sort()

    for i in ans:
        print(' '.join(map(str, i)))