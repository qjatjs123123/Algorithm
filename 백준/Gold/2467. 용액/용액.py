import sys


def solution():
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()

    def binary_search(target):
        start = 0
        end = n - 1

        while start <= end:
            mid = (start + end) // 2

            if arr[mid] > target:
                end = mid - 1
            else:
                start = mid + 1

        return start

    ans = float('inf')
    tmp = []
    for i in range(n):
        num = arr[i]
        idx = binary_search(num*-1)

        for case in [idx+1, idx, idx-1]:

            if i != case and 0 <= case < n and ans > abs(num + arr[case]):
                ans = abs(num + arr[case])
                tmp = [num, arr[case]]
    tmp.sort()
    print(' '.join(map(str, tmp)))

solution()