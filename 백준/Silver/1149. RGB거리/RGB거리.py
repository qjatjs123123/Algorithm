for test_case in range(1):
    n = int(input())
    dp = [[0,0,0]]
    for _ in range(n):
        tmp = list(map(int, input().split()))
        arr = [tmp[0] + min(dp[-1][1], dp[-1][2]), tmp[1] + min(dp[-1][0], dp[-1][2]), tmp[2] + min(dp[-1][1], dp[-1][0])]
        dp.append(arr)
    print(min(dp[-1]))
