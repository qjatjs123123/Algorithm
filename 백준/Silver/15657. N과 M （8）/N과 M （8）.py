import sys
import copy

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    arr = list(map(int, sys.stdin.readline().split()))
    tmp = []
    ans = []
    def backtracking(l):
        if l == m:
            ans.append(copy.deepcopy(tmp))
            return

        for num in arr:
            if not tmp:
                tmp.append(num)
                backtracking(l+1)
                tmp.pop()
            else:
                if tmp[-1] <= num:
                    tmp.append(num)
                    backtracking(l + 1)
                    tmp.pop()
    backtracking(0)
    ans.sort()

    for i in ans:
        print(' '.join(map(str, i)))