from heapq import heappush, heappop
from collections import deque
from itertools import combinations, permutations
import math
import copy

tc = int(input())
for test_case in range(1, tc + 1):
    n, m = map(int, input().split())
    q = deque()
    q1 = []
    arr = list(map(int, input().split()))
    for i in range(len(arr)):
        q.append([arr[i], i])
        heappush(q1, -arr[i])
    ans = 0
    while True:

        max_num = -heappop(q1)
        flg = False
        while True:
            cur_num = q.popleft()
            if max_num == cur_num[0]:
                ans += 1
                if m == cur_num[1]:
                    print(ans)
                    flg = True
                break

            q.append(cur_num)
        if flg:
            break