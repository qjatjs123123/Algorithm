import sys
import copy

for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    flg = False
    for i in range(n-1, 0, -1):
        if arr[i] > arr[i-1]:
            target = arr[i-1]

            for j in range(n-1, i-1, -1):
                if target < arr[j]:
                    arr[i-1] = arr[j]
                    arr[j] = target
                    break

            ans = arr[:i] + sorted(arr[i:])
            print(' '.join(map(str, ans)))
            flg = True
            break

    if not flg:
        print(-1)