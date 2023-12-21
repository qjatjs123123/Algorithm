import sys

def solution():
    string = ' '+sys.stdin.readline().rstrip()

    dp = [0 for _ in range(len(string))]
    dp[0] = 1
    flg1 = False
    for i in range(1, len(string)):
        flg = False
        total = 0
        for j in range(max(0, i-1), i + 1):
            if len(string[j:i+1]) == 2 and string[j:i+1][0] == '0':
                continue
            if 1 <= int(string[j:i+1]) <= 26:
                total += dp[j-1]
                flg = True

        if not flg:
            flg1= True
            print(0)
            break
        dp[i] = total % 1000000
    if not flg1:
        print(dp[-1])
solution()