import sys


for test_case in range(1):
    dp = {}
    def dfs(a, b, c):
        if (a, b, c) in dp:
            return dp[(a,b,c)]
        if a <= 0 or b <= 0 or c <= 0:
            dp[(a,b,c)] = 1
            return dp[(a,b,c)]
        elif a > 20 or b > 20 or c > 20:
            dp[(a, b, c)] = dfs(20, 20, 20)
            return dp[(a, b, c)]
        elif a < b and b < c:
            dp[(a, b, c)] = dfs(a, b, c-1) + dfs(a, b-1, c-1) - dfs(a, b-1, c)
            return dp[(a, b, c)]
        else:
            dp[(a, b, c)] = dfs(a-1, b, c) + dfs(a-1, b-1, c) + dfs(a-1, b, c-1) - dfs(a-1, b-1, c-1)
            return dp[(a, b, c)]


    while True:
        a, b, c = map(int, sys.stdin.readline().split())

        if a == -1 and b == -1 and c == -1:
            break
        ans = "w(" + str(a) +", " + str(b) + ", " + str(c) +") = "
        print(ans + str(dfs(a, b, c)))
