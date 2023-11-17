import sys
from collections import deque

for test_case in range(1):
    n = int(sys.stdin.readline())

    arr = list(map(int, sys.stdin.readline().split()))
    tmp = []

    for i in range(1, n + 1):
        tmp.append([arr[i-1], i])

    q = deque(tmp)
    ans = []
    while q:
        tmp_arr = q.popleft()
        ans.append(tmp_arr[1])
        step = tmp_arr[0]

        if not q:
            break

        if step > 0:
            for _ in range(step-1):
                q.append(q.popleft())
        else:
            for _ in range(abs(step)):
                q.appendleft(q.pop())

    print(' '.join(map(str, ans)))