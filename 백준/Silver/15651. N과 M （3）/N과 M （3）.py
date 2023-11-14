import sys

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    tmp = []
    ans = []
    def backtracking(l):
        if l == m:
            ans.append(' '.join(map(str,tmp)))
            return
        for i in range(1, n + 1):

            tmp.append(i)
            backtracking(l+1)
            tmp.pop()


    backtracking(0)
    for a in ans:
        print(a)