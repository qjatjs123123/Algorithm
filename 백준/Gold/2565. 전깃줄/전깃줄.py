import sys

for test_case in range(1):
    n = int(sys.stdin.readline())
    lines = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

    lines.sort()
    dp = [1 for i in range(n)]
    dp[0] = 1

    for i in range(1, n):
        for j in range(i):
            if lines[i][1] > lines[j][1]:
                dp[i] = max(dp[i], 1 + dp[j])

    print(n - max(dp))
