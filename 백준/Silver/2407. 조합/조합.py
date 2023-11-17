import sys
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    m = min(m, n-m)

    parent = 1
    for i in range(m):
        parent *= n-i

    child = 1
    for i in range(1,m+1):
        child *= i

    print(parent // child)