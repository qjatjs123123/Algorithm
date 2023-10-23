import sys
from collections import deque
sys.setrecursionlimit(10**6)
from heapq import heappush, heappop
from itertools import combinations
import math

for test_case in range(1):
    stack_left = list(sys.stdin.readline().rstrip())
    stack_right = deque([])

    cmd_cnt = int(sys.stdin.readline())


    for _ in range(cmd_cnt):
        cmd = sys.stdin.readline().split()
        if cmd[0] == 'P':
            stack_left.append(cmd[1])
        elif cmd[0] == 'L' and stack_left:
            stack_right.appendleft(stack_left.pop())
        elif cmd[0] == 'D' and stack_right:
            stack_left.append(stack_right.popleft())
        elif cmd[0] == 'B' and stack_left:
            stack_left.pop()


    print(''.join(stack_left+list(stack_right)))
