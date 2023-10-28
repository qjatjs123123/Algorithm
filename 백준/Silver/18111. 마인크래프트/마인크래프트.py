import sys

for test_case in range(1):
    n, m, b = map(int, sys.stdin.readline().split())
    graph = []
    dp = {}

    for _ in range(n):
        tmp = list(map(int, sys.stdin.readline().split()))
        for num in tmp:
            if num not in dp:
                dp[num] = 1
            else:
                dp[num] += 1


    ans = []
    for earthHeight in range(257):
        inventory = b
        needs = 0
        time = 0

        for height in list(dp.keys()):
            cnt = dp[height]
            if height >= earthHeight:
                inventory += (height - earthHeight)*cnt
                time += 2*(height - earthHeight)*cnt
            else:
                needs += (earthHeight - height)*cnt
                time += (earthHeight - height)*cnt
        if inventory >= needs:
            ans.append([time, earthHeight])

    ans.sort(key=lambda x:(x[0], -x[1]))

    print(' '.join(map(str,ans[0])))
