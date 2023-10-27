import sys
from itertools import permutations

def solution():
    for test_case in range(1):
        n = int(sys.stdin.readline())

        arr = []
        for _ in range(n):
            arr.append(list(map(int, sys.stdin.readline().split())))

        node = [i for i in range(n)]

        cases = list(permutations(node, n))
        ans = float("inf")
        for case in cases:
            tmp = list(case) + [case[0]]
            total = 0
            flg = False
            for i in range(len(tmp)-1):
                start, end =  tmp[i], tmp[i+1]
                if arr[start][end] == 0:
                    flg = True
                    break
                total += arr[start][end]
            if not flg:
                ans = min(ans, total)
    print(ans)
solution()