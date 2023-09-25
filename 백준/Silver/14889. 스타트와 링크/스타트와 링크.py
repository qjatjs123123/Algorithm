
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
    n = int(sys.stdin.readline())

    arr = []
    for _ in range(n):
        arr.append(list(map(int, sys.stdin.readline().split())))

    members = [i for i in range(n)]
    cases = (list(combinations(members,n//2)))
    ans = 999999
    for case in cases:
        members = [i for i in range(n)]
        teamA = 0
        teamB = 0
        for c in case:
            members.remove(c)
            for num in case:
                teamA += arr[c][num]

        for c in members:
            for num in members:
                teamB += arr[c][num]

        ans = min(ans, abs(teamB-teamA))

    print(ans)