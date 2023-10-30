from heapq import heappush, heappop
import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = []
    for _ in range(n):
        for num in list(map(int, sys.stdin.readline().split())):
            heappush(arr, num)
            if len(arr) > 1500:
                heappop(arr)

    arr.sort(reverse=True)
    print(arr[n-1])
