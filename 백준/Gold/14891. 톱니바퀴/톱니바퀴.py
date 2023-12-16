import sys

from collections import deque

for test_case in range(1):
    arr = [deque(list(map(int, sys.stdin.readline().rstrip()))) for _ in range(4)]
    k = int(sys.stdin.readline())

    for _ in range(k):
        num, direct = map(int, sys.stdin.readline().split())
        stack = []
        r = direct
        ## 왼쪽
        for i in range(num-2, -1, -1):
            left = arr[i]
            right = arr[i + 1]

            if left[2] == right[6]:
                break
            r *= -1
            stack.append([i, r])
        r = direct
        for i in range(num, 4):
            left = arr[i-1]
            right = arr[i]

            if left[2] == right[6]:
                break
            r *= -1
            stack.append([i, r])

        while stack:
            n, r = stack.pop()
            arr[n].rotate(r)

        arr[num-1].rotate(direct)

    ans = 0

    for i in range(4):
        if arr[i][0] == 1:
            ans += 2**i

    print(ans)