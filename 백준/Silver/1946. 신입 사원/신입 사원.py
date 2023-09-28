import sys
t = int(sys.stdin.readline())
for test_case in range(t):
    n = int(sys.stdin.readline())
    arr = []
    dp = {}
    meet_score = 0
    for _ in range(n):
        tmp = list(map(int, sys.stdin.readline().split()))
        if tmp[0] == 1:
            meet_score = tmp[1]
        dp[tmp[1]] = tmp[0]
    ans = 1
    max_num = 0

    for i in range(1, meet_score + 1):

        if i == 1:
            max_num = dp[i]
            continue
        if max_num > dp[i]:
            ans += 1
            max_num = dp[i]

    print(ans)