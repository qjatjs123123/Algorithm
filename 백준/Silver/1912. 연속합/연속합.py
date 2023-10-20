import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    cur_max, total_max = -1001, -1001
    for num in arr:
        cur_max = max(cur_max+num, num)
        total_max = max(total_max, cur_max)
    print(total_max)