import copy
import sys
for test_case in range(1):
    n = int(input())
    arr = []
    for _ in range(n):
        tmp = list(map(int, input().split()))
        arr.append(tmp)

    for i in range(len(arr) - 1):
        tmp = copy.deepcopy(arr[i + 1])
        for j in range(len(arr[i])):
            for k in range(j, j+2):
                arr[i+1][k] = max(arr[i+1][k], arr[i][j] + tmp[k])

    print(max(arr[-1]))