import sys
for test_case in range(1):
    s1, s2 = sys.stdin.readline().rstrip(), sys.stdin.readline().rstrip()

    dp = [[0 for i in range(len(s1) + 1)] for j in range(len(s2) + 1)]

    for i in range(1, len(s2) + 1):
        for j in range(1, len(s1) + 1):
            if s2[i - 1] == s1[j - 1]:
                dp[i][j] = dp[i-1][j-1] + 1
                continue
            dp[i][j] = max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])

    print(dp[len(s2)][len(s1)])