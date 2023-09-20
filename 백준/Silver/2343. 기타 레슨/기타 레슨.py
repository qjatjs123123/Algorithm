import sys
from collections import deque

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    start = max(arr)
    end = sum(arr)


    ans = 0
    while start<end:
        cnt = 1
        total = 0
        mid = (start + end) // 2
        for num in arr:
            if total + num > mid:
                cnt += 1
                total = 0
            total += num

        if cnt > m:
            start = mid + 1
        else:
            end = mid




    print(start)