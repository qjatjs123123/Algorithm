import sys
from heapq import heappush, heappop
from collections import deque
from bisect import bisect_left, bisect_right
from itertools import combinations
sys.setrecursionlimit(10**6)
import copy
import math
# t = int(sys.stdin.readline())

for test_case in range(1):
    a, b, c = map(int, sys.stdin.readline().split())

    def dfs(b):
        if b == 1:
            return a % c
        if b % 2 != 0:
            num = dfs(b // 2)
            return ((num%c) * (num%c) * a)%c
        else:
            num = dfs(b // 2)
            return ((num%c) * (num%c))%c

    print(dfs(b))
