import sys
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    if n == 1:
        print(1)

    elif n == 2:
        if m <= 2:
            print(1)
        elif  m <= 4:
            print(2)
        elif m <= 6:
            print(3)
        else:
            print(4)

    else:
        if m <= 4:
            print(m)
        elif m <= 6:
            print(4)
        else:
            print(m-7 + 5)