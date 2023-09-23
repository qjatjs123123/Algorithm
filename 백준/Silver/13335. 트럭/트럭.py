import sys
from heapq import heappush, heappop
from collections import deque
sys.setrecursionlimit(10**6)
import copy
import math
# t = int(sys.stdin.readline())
input = sys.stdin.readline
for test_case in range(1):
    n, w, l = map(int, sys.stdin.readline().split())
    q = deque(list(map(int, sys.stdin.readline().split())))
    trucks = deque([0 for _ in range(w)])
    total = 0
    time = 0

    while trucks:
        total -= trucks.popleft()

        if q:
            if total + q[0] > l:
                trucks.append(0)
            else:
                num = q.popleft()
                trucks.append(num)
                total += num

        time += 1
    print(time)