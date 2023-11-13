import sys
import math
for test_case in range(1):
    n = int(sys.stdin.readline())
    for _ in range(n):
        x1,y1,r1,x2,y2,r2 = map(int, sys.stdin.readline().split())

        d = math.sqrt((x1-x2)**2 + (y1-y2)**2)

        if r1 + r2 < d or abs(r1-r2) > d:
            print(0)
        elif r1 == r2 and x1 == x2 and y1 == y2:
            print(-1)
        elif r1 + r2 == d or abs(r1-r2) == d:
            print(1)
        else:
            print(2)