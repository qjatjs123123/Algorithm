import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))

    flg = False
    for i in range(n-1, 0, -1):
        if arr[i] < arr[i-1]:

            for j in range(n-1, i-1, -1):
                if arr[i-1] > arr[j]:
                    tmp = arr[i-1]
                    arr[i-1] = arr[j]
                    arr[j] = tmp
                    break

            a = arr[i:]
            a.sort(reverse=True)
            tmp = arr[:i] + a
            print(' '.join(map(str, tmp)))
            flg = True
            break

    if not flg:
        print(-1)

