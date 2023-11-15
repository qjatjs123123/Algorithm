import sys
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    dp = [0]
    for num in arr:
        dp.append(dp[-1] + num)

    for _ in range(m):
        start, end = map(int, sys.stdin.readline().split())
        print(dp[end] - dp[start-1])
