import sys

for test_case in range(1):

    a, b = map(int, sys.stdin.readline().split())

    if a < b:
        tmp = a
        a = b
        b = tmp

    while True:
        if a % b == 0:
            print("1"*b)
            break
        tmp = a
        a = b
        b = tmp % b
