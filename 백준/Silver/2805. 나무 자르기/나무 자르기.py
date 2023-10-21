import sys
from collections import deque
import math
for test_case in range(1):
    n, m =  map(int, sys.stdin.readline().split())

    trees = list(map(int, sys.stdin.readline().split()))
    start = 0
    end = max(trees)

    while start <= end:
        mid = (start + end) // 2

        total = 0
        for tree in trees:
            if tree > mid:
                total += tree - mid

            if total >= m:
                break

        if total >= m:
            start = mid + 1
        else:
            end = mid - 1

    print(end)
