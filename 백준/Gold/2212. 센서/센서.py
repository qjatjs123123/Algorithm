import sys


def solution():
    n = int(sys.stdin.readline())
    k = int(sys.stdin.readline())

    censor = list(map(int, sys.stdin.readline().split()))
    censor.sort()
    gap = []
    for i in range(n-1):
        gap.append(abs(censor[i + 1] - censor[i]))
    gap.sort()
    for _ in range(k-1):
        if gap:
            gap.pop()
    print(sum(gap))
solution()