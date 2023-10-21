import sys
from collections import deque
import math
for test_case in range(1):
    prime = [False, False] + [True for _ in range(300000)]

    for i in range(2, 300001):

        if prime[i]:
            for j in range(2*i, 300001, i):
                prime[j] = False


    while True:
        num = int(sys.stdin.readline())
        if num == 0:
            break
        ans = 0
        for i in range(num+1, 2*num + 1):
            if prime[i]:
                ans += 1

        print(ans)