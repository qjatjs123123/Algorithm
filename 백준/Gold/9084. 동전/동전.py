import sys

def solution():
    t = int(sys.stdin.readline())

    for _ in range(t):
        n = int(sys.stdin.readline())
        coin = list(map(int, sys.stdin.readline().split()))
        money = int(sys.stdin.readline())

        dp = [0 for _ in range(money + 1)]
        dp[0] = 1

        for c in coin:
            for m in range(money + 1):
                if dp[m] != 0 and m + c <= money:
                    dp[m+c] += dp[m]

        print(dp[-1])
solution()