import sys


for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    dp = [0 for _ in range(n)]
    dp[0] = 1
    for i in range(1, n):
        dp[i] = 1
        for j in range(i-1, -1, -1):
            if arr[i] < arr[j]:
                dp[i] = max(dp[i], 1 + dp[j])

    print(max(dp))
