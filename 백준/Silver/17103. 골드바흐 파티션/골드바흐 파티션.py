import sys

for test_case in range(1):
    n = int(sys.stdin.readline())

    prime = [False, False] + [True for _ in range(1000001)]

    for i in range(2, 1000001):
        if prime[i]:
            for j in range(2*i, 1000001, i):
                prime[j] = False

    for _ in range(n):
        even = int(sys.stdin.readline())
        ans = 0
        for i in range(2, (even // 2) + 1):
            if prime[i] and prime[even - i]:
                ans += 1
        print(ans)