import sys
for test_case in range(1):
    n = int(sys.stdin.readline())

    prime = [False, False] + [True for i in range(1100000)]

    for i in range(2, 1100001):
        flg = False
        if prime[i]:
            if i >= n:
                s = str(i)
                if s == s[::-1]:
                    flg = True
                    print(s)
                    break
            if flg:
                break
            for j in range(i*2, 1100001, i):
                prime[j] = False