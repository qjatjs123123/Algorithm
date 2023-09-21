import sys
for test_case in range(1):
    n, s, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    dp = [[0 for _ in range(m+1)] for _ in range(n + 1)]

    dp[0][s] = 1

    for i in range(n):
        for j in range(m + 1):
            if dp[i][j] == 1 and j - arr[i] >= 0:
                dp[i+1][j - arr[i]] = 1
            if dp[i][j] == 1 and j + arr[i] <= m:
                dp[i+1][j + arr[i]] = 1
    ans = -1
    for i in range(m + 1):
        if dp[n][i] == 1:
            ans = i
    print(ans)