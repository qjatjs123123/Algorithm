import math
T = int(input())
case = []
for test_case in range(1, T+1):
    n = int(input())
    max_num = int(math.sqrt(n))
    flg = False
    for i in range(1, max_num + 1):
        if n % i == 0:
            j = n // i
            if i >= 1 and i <= 9 and j >= 1 and j <= 9:
                print("#" + str(test_case) +" " + "Yes")
                flg  = True
                break

    if not flg:
        print("#" + str(test_case) + " " + "No")