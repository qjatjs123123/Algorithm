import sys
for test_case in range(1):
    n, s, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    dp = [set()] + [set() for _ in range(n)]
    dp[0].add(s)

    for i in range(1, n + 1):
        for prev in dp[i-1]:
            if prev + arr[i-1] <= m:
                dp[i].add(prev+arr[i-1])
            if prev - arr[i-1] >= 0:
                dp[i].add(prev-arr[i-1])
    if not dp[n]:
        print(-1)
    else:
        print(max(dp[n]))
