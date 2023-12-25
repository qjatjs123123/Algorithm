import sys
from collections import deque


def solution():
    s = sys.stdin.readline().rstrip()
    t = sys.stdin.readline().rstrip()

    q = deque(t)

    while len(s) != len(q):

        if q[-1] == 'A':
            q.pop()

        else:
            q.pop()
            q.reverse()

    if s == ''.join(q):
        print(1)
    else:
        print(0)

solution()