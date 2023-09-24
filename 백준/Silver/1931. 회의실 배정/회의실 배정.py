for test_case in range(1):
    n = int(input())
    arr = []
    for _ in range(n):
        arr.append(list(map(int, input().split())))
    arr.sort(key=lambda x:(x[1],x[0]))
    idx = 0
    cnt = 1
    while True:
        start, end = arr[idx][0], arr[idx][1]
        flg = False
        for i in range(idx+1, len(arr)):
            if arr[i][0] >= end:
                idx = i
                flg = True
                break
        if not flg:
            break
        cnt += 1
    print(cnt)