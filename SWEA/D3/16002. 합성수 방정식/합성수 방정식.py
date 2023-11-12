import math
T = int(input())
for test_case in range(1, T+1):
    n = int(input())

    def isPrime(num):
        max_num = int(math.sqrt(num))
        isPrime = False
        for i in range(2, max_num + 1):
            if num % i == 0:
                isPrime = True
                break
        return isPrime

    for i in range(2, 10**9 + 1):
        x, y = i, i+n

        if isPrime(x) and isPrime(y):
            print("#" + str(test_case) + " " +str(y) + " " + str(x))
            break