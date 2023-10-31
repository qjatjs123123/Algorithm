from bisect import bisect_left, bisect_right
for test_case in range(1):
    n, m = map(int, input().split())

    prime = [False, False] + [True for i in range(m-1)]
    primes =[]

    for i in range(2, len(prime)):
        if prime[i]:
            primes.append(i)
            for j in range(i*2, len(prime), i):
                prime[j] = False
    idx = bisect_left(primes, n)
    for i in range(idx, len(primes)):
        print(primes[i])
