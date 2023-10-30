import sys
import copy

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))

    tmp = []
    dp = {}
    ans = []
    def backtracking():
        if len(tmp) == m:
            if tuple(tmp) not in dp:
                ans.append(copy.deepcopy(tmp))
                dp[tuple(tmp)] = True
            return

        for num in arr:
            tmp.append(num)
            backtracking()
            tmp.pop()

    backtracking()
    ans.sort()
    for a in ans:
        print(' '.join(map(str, a)))

