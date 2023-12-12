import sys

for test_case in range(1):
    n = int(sys.stdin.readline())

    for _ in range(n):
        x, y = map(int, sys.stdin.readline().split())

        dist = y - x

        hap = 0
        p = 1
        while True:
            if hap >= dist:
                break
            hap += 2*p
            p += 1

        if hap - (p - 1) + 1 <= dist:
            print((p - 1)* 2)
        else:
            print(((p - 1)* 2) - 1)