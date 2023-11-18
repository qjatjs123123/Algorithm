for test_case in range(1):
    n = int(input())
    dp = {}
    for _ in range(n):
        s = input()
        if len(s) not in dp:
            dp[len(s)] = [s]
        else:
            dp[len(s)] += [s]

    keys = list(dp.keys())
    keys.sort()

    for key in keys:
        arr = dp[key]
        stack = []
        for s in arr:
            total = 0
            for ss in s:
                if ss.isdigit():
                    total += int(ss)
            stack.append([total, s])
        stack.sort(key=lambda x:(x[0],x[1]))
        for st in stack:
            print(st[1])
