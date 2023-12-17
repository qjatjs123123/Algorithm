import sys
from bisect import bisect_left,bisect_right
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()

    min_total = float('inf')
    result = []
    for i in range(n):
        num = arr[i]
        idx = bisect_left(arr, num*-1)

        cases = [idx, idx + 1, idx - 1]
        min_num = float('inf')
        tmp = -1
        for case in cases:
            if 0 <= case < n:
                if min_num > abs(num + arr[case]):
                    min_num = abs(num + arr[case])
                    tmp = case
        if i == tmp:
            continue
        if min_total > min_num:
            min_total = min_num
            result = [num, arr[tmp]]

    result.sort()
    print(' '.join(map(str, result)))