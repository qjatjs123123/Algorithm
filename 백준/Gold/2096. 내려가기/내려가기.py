import sys

for test_case in range(1):
    n = int(sys.stdin.readline())

    dp = [[0, 0] for _ in range(3)]

    for i in range(n):
        arr = list(map(int, sys.stdin.readline().split()))

        if i == 0:
            dp[0][0], dp[0][1] = arr[0], arr[0]
            dp[1][0], dp[1][1] = arr[1], arr[1]
            dp[2][0], dp[2][1] = arr[2], arr[2]
            continue

        min_one, min_two, min_three = min(dp[0][0], dp[1][0]), min(dp[0][0], dp[1][0], dp[2][0]), min(dp[1][0], dp[2][0])
        max_one, max_two, max_three = max(dp[0][1], dp[1][1]), max(dp[0][1], dp[1][1], dp[2][1]), max(dp[1][1], dp[2][1])

        dp[0][0], dp[1][0], dp[2][0] = min_one + arr[0], min_two + arr[1], min_three + arr[2]
        dp[0][1], dp[1][1], dp[2][1] = max_one + arr[0], max_two + arr[1], max_three + arr[2]

    print(max(i[1] for i in dp), min(i[0] for i in dp))