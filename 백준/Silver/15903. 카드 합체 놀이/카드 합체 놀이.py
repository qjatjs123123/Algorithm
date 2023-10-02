import sys
from heapq import heappush, heappop

# t = int(sys.stdin.readline())
input = sys.stdin.readline
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    q = []
    for num in arr:
        heappush(q, num)

    for _ in range(m):
        n1, n2 = heappop(q), heappop(q)
        heappush(q, n1 + n2)
        heappush(q, n1 + n2)

    print(sum(q))