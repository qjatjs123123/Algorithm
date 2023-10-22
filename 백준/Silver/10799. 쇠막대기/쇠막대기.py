import sys
from collections import deque
input = sys.stdin.readline
from heapq import heappush, heappop
from itertools import combinations
import math
for test_case in range(1):

    arr = sys.stdin.readline()
    stack = []
    ans = []
    for cmd in arr:
        if not stack:
            stack.append(cmd)
            continue

        if cmd == ')' and stack[-1] == '(':
            new_cmd = stack.pop()
            if new_cmd != ')' and new_cmd != '(':
                stack.append(new_cmd + 1)
            else:
                stack.append(1)
        elif cmd == ')' and stack[-1] != '(':
            cnt = 0
            while stack:
                stack_cmd = stack.pop()
                if stack_cmd == '(':
                    ans.append(cnt)
                    break
                cnt += stack_cmd
            stack.append(cnt)
        else:
            stack.append(cmd)
    print(sum(ans) + len(ans))