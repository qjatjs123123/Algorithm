import sys
import copy

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    ans = []
    tmp = []

    def backtracking(l):
        if l == m:
            a = ' '.join(map(str, tmp))
            ans.append(a)
            return

        for i in range(n):
            tmp.append(arr[i])
            backtracking(l + 1)
            tmp.pop()

    backtracking(0)


    for i in ans:
        print(i)