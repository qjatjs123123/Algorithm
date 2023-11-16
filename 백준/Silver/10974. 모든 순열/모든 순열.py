import sys
for test_case in range(1):
    n = int(sys.stdin.readline())

    ans = []
    visited = [False for i in range(n + 1)]
    tmp = []

    def backtracking(l):
        if l == n:
            ans.append(' '.join(map(str, tmp)))
            return

        for i in range(1, n+1):
            if not visited[i]:
                tmp.append(i)
                visited[i] = True
                backtracking(l + 1)
                visited[i] = False
                tmp.pop()

    backtracking(0)

    for i in ans:
        print(i)
