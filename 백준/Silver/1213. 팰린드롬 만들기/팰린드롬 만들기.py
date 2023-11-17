for test_case in range(1):
    s = input()
    dp = {}
    for alpha in s:
        if alpha not in dp:
            dp[alpha] = 1
        else:
            dp[alpha] += 1

    keys = list(dp.keys())
    odd_alpha = ''
    odd_cnt = 0
    flg = True
    for key in keys:
        if dp[key] % 2 != 0:
            odd_alpha = key
            odd_cnt += 1

        if odd_cnt > 1:
            flg = False
            print("I'm Sorry Hansoo")
            break
    keys.sort()
    ans = ""
    stack = []
    if flg:
        for key in keys:
            tmp = key * (dp[key] // 2)
            ans += tmp
            stack.append(tmp)
        if odd_cnt == 1:
            ans += odd_alpha
        while stack:
            ans += stack.pop()
        print(ans)