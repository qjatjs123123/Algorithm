import sys
import math
for test_case in range(1):
    x1, y1 = map(int, sys.stdin.readline().split())
    x2, y2 = map(int, sys.stdin.readline().split())

    gcd = math.gcd(y1,y2)
    parent = gcd * (y1//gcd) *(y2//gcd)
    child = x1 * (parent // y1) + x2 * (parent // y2)

    gcd = math.gcd(parent, child)

    print(child // gcd, parent // gcd)
