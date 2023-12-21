import sys


def solution():
    a = list(map(int, sys.stdin.readline().split()))
    b = list(map(int, sys.stdin.readline().split()))
    c = list(map(int, sys.stdin.readline().split()))

    ab = [b[0] - a[0], b[1] - a[1]]
    bc = [c[0] - b[0], c[1] - b[1]]

    ans = ab[0]*bc[1] - ab[1]*bc[0]

    if ans > 0:
        print(1)
    elif ans == 0:
        print(0)
    else:
        print(-1)

solution()