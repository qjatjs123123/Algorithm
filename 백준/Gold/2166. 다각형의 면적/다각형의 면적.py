import sys


def solution():
    n = int(sys.stdin.readline())

    arr = list(list(map(int, sys.stdin.readline().split())) for _ in range(n))
    arr.append(arr[0])

    left, right = 0, 0
    for i in range(n):
        left += arr[i][0]*arr[i+1][1]
        right += arr[i][1]*arr[i+1][0]


    ans = abs(left - right) / 2
    print(round(ans, 1))
solution()