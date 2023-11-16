import sys
for test_case in range(1):
    n = int(sys.stdin.readline())

    arr = list(map(int, sys.stdin.readline().split()))

    target = int(sys.stdin.readline())

    dp = {}

    for num in arr:

        if num not in dp:
            dp[num] = 1
        else:
            dp[num] += 1

    ans = set()

    for num in arr:
        tmp = target - num
        if tmp == num and dp[tmp] == 1:
            continue
        new_arr = [tmp, num]
        new_arr.sort()
        if tmp in dp:
            ans.add(tuple(new_arr))

    print(len(ans))