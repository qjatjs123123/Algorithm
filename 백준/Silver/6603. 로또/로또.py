import sys
from collections import deque
sys.setrecursionlimit(10**6)
input = sys.stdin.readline
from heapq import heappush, heappop
from itertools import combinations
import math

for test_case in range(1):

    while True:
        arr = list(map(int, sys.stdin.readline().split()))
        first = arr.pop(0)
        if first == 0:
            break

        for tmp in list(combinations(arr, 6)):
            print(' '.join(map(str, tmp)))
        print()