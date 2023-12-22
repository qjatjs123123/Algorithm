import sys

def solution():
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    dp = [0 for _ in range(21)]

    for i in range(n - 1):
        tmp = [0 for _ in range(21)]
        if i == 0:
            dp[arr[0]] = 1
            continue
        for j in range(21):
            if dp[j] != 0:
                plus = arr[i] + j
                minus = j - arr[i]
                if 0 <= plus <= 20:
                    tmp[plus] += dp[j]
                if 0 <= minus <= 20:
                    tmp[minus] += dp[j]
        dp = tmp

    print(dp[arr[-1]])
solution()