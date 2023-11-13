import sys
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    arr = [i for i in range(1, n + 1)]

    visited = [False for i in range(n+1)]
    tmp = []
    ans = []
    def backtracking(l):

        if l == m:
            ans.append(' '.join(map(str, tmp)))
            return

        for num in arr:
            if not tmp:
                visited[num] = True
                tmp.append(num)
                backtracking(l + 1)
                visited[num] = False
                tmp.pop()
                continue


            if not visited[num] and num > tmp[-1]:
                visited[num] = True
                tmp.append(num)
                backtracking(l+1)
                visited[num] = False
                tmp.pop()

    backtracking(0)

    for i in ans:
        print(i)