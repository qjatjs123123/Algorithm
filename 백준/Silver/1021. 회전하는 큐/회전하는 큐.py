import sys
from collections import deque
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int ,sys.stdin.readline().split()))

    tmp = deque([i for i in range(1, n + 1)])
    ans = 0
    for target in arr:
        idx = tmp.index(target)
        right = idx
        left = len(tmp) - idx + 1

        while True:
            if tmp[0] == target:
                tmp.popleft()
                break

            if right < left:
                tmp.append(tmp.popleft())
            else:
                tmp.appendleft(tmp.pop())
            ans += 1
    print(ans)