import sys
for test_case in range(1):
    n, k = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))

    cur = 0
    ans = 0
    for i in range(n-k+1):
        if i == 0:
            cur = sum(arr[i:i+k])
            ans = cur
            continue
        cur += arr[i+k-1] - arr[i-1]
        ans = max(ans, cur)

    print(ans)