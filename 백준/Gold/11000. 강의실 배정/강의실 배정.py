import sys
from heapq import heappush, heappop


def solution():
    n = int(sys.stdin.readline())
    arr = []

    for _ in range(n):
        start, end = map(int, sys.stdin.readline().split())
        arr.append([start, end])
    arr.sort()

    room = []
    heappush(room, arr[0][1])

    for i in range(1, n):
        if arr[i][0] >= room[0]:
            heappop(room)
            heappush(room, arr[i][1])
        else:
            heappush(room, arr[i][1])
    print(len(room))

solution()
