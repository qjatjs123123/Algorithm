import sys

from collections import deque

for test_case in range(1):
    n = int(sys.stdin.readline())
    tmp = list(map(int, sys.stdin.readline().split()))
    height = []

    for i in range(1, n + 1):
        height.append([tmp[i - 1], i])

    stack = []
    height.reverse()
    ans = []
    for i in range(n):
        h, idx = height.pop()
        flg = True

        if not stack:
            stack.append([h, idx])
            ans.append(0)
            continue

        while stack:
            top_h, top_idx = stack[-1]

            if top_h >= h:
                ans.append(top_idx)
                flg = False
                break

            stack.pop()
        if flg:
            ans.append(0)
        stack.append([h, idx])
    print(' '.join(map(str, ans)))