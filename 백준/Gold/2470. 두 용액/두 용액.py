import sys

for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()

    start = 0
    end = n - 1
    ans = float('inf')
    tmp = []
    while start < end:
        cur = arr[start] + arr[end]

        if ans > abs(cur):
            ans = abs(cur)
            tmp = [arr[start], arr[end]]

        if cur == 0:
            break

        if cur > 0:
            end -= 1
        else:
            start += 1

    tmp.sort()

    print(' '.join(map(str, tmp)))