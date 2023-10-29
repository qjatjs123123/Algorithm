import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    dp = [1 for i in range(n)]
    ans = 0
    for i in range(n):
        for j in range(i-1, -1, -1):
            if arr[i] > arr[j]:
                dp[i] = max(dp[i], dp[j]+1)
        ans = max(ans, dp[i])
    print(ans)