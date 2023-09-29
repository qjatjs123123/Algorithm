import sys
from collections import deque


# t = int(sys.stdin.readline())

prime = [False, False] + [True for i in range(1000000)]
dp = {}
for i in range(2, 1000001):

    if prime[i]:
        if i != 2:
            dp[i] = True
        for j in range(2*i, 1000001, i):
            prime[j] = False
primes = list(dp.keys())
while True:
    n = int(sys.stdin.readline())
    if n == 0:
        break
    flg = False
    for p in primes:
        if p > n // 2:
            break
        if n-p in dp:
            print(str(n) + " = " + str(p) + " + " + str(n-p))
            flg = True
            break

    if not flg:
        print("Goldbach's conjecture is wrong.")