import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())
    arr = [0 for i in range(n)]
    for _ in range(m):
        num=int(sys.stdin.readline())
        arr[num-1] = num

    dp = []
    cnt = 0
    for i in range(n):
        if arr[i] != 0:
            dp.append(cnt)
            cnt = 0
            continue
        if i == n-1:
            dp.append(cnt+1)
        cnt += 1

    max_num = max(dp)

    tmp = [0 for i in range(max_num + 5)]
    tmp[0] = 1
    tmp[1] = 1
    for i in range(2, max_num+1):
        tmp[i] = tmp[i-1] + tmp[i-2]
    ans = 1
    for num in dp:
        ans *= tmp[num]
    print(ans)