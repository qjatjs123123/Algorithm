for test_case in range(1):
    n = int(input())
    arr = [0] + list(map(int, input().split()))

    dp = [[0 for i in range(n+1)] for j in range(n+1)]

    for i in range(1, len(arr)):
        target = arr[i]
        for card_cnt in range(1, n + 1):
            if i == 0:
                dp[i][card_cnt-1] = target * card_cnt
                continue
            cnt = card_cnt // i
            for k in range(cnt+1):
                dp[i][card_cnt] = max(dp[i][card_cnt], target*k + dp[i-1][card_cnt - i*k])
    print(dp[-1][-1])
