import sys
from collections import deque
sys.setrecursionlimit(10**6)
from heapq import heappush, heappop
from itertools import combinations
import math

for test_case in range(1):
    n, k = map(int, sys.stdin.readline().split())

    min_num = min(k, n-k)

    p = 1
    for i in range(n, n-min_num, -1):
        p *= i
    c = 1
    for i in range(1, min_num + 1):
        c *= i

    print((p//c) % 10007)