import sys
from collections import deque
input = sys.stdin.readline
from heapq import heappush, heappop
import math
for test_case in range(1):
    n = int(sys.stdin.readline())

    q = []

    for _ in range(n):
        cmd = int(sys.stdin.readline())

        if cmd == 0 and not q:
            print(0)
        elif cmd == 0 and q:
            print(heappop(q))
        else:
            heappush(q, cmd)
