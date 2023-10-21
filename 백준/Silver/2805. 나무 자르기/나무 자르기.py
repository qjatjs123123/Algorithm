import sys
from collections import deque
import math
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    height = list(map(int ,sys.stdin.readline().split()))

    start = 0
    end = max(height)

    while start < end:
        mid = (start + end) // 2

        total = 0
        for tree_height in height:
            if tree_height >= mid:
                total += tree_height - mid

            if total >= m:
                break

        if total >= m:
            start = mid + 1

        else:
            end = mid

    print(start-1)