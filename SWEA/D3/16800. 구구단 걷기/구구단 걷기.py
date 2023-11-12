import math
T = int(input())
for test_case in range(1, T+1):
    n = int(input())

    max_num = int(math.sqrt(n))

    for i in range(max_num, 0, -1):
        if n % i == 0:
            ans = i + n//i - 2
            print("#" + str(test_case) + " " + str(ans))
            break