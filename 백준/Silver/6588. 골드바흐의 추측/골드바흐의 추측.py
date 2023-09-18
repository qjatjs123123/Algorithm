import sys
from collections import deque

# t = int(sys.stdin.readline())

prime = [False, False] + [True for i in range(1000000)]
primes = []
for i in range(2, 1000001):

    if prime[i]:
        primes.append(i)
        for j in range(2*i, 1000001, i):
            prime[j] = False
primes.pop(0)
while True:
    n = int(sys.stdin.readline())
    if n == 0:
        break
    flg = False
    for p in primes:
        if p > n // 2:
            break
        if prime[n-p]:
            print(f'{n} = {p} + {n-p}')
            flg = True
            break

    if not flg:
        print("Goldbach's conjecture is wrong.")