T = int(input())
case = []
for test_case in range(1, T+1):
    n,d,g = map(int, input().split())

    if d != 0 and g == 0:
        print("#" + str(test_case) + " " + "Broken")
    elif d != 100 and g == 100:
        print("#" + str(test_case) + " " + "Broken")
    else:
        flg = False
        for cnt in range(1, min(101, n+1)):
            num = d*cnt
            if num % 100 == 0:
                flg = True
                break
        if flg:
            print("#" + str(test_case) + " " + "Possible")
        else:
            print("#" + str(test_case) + " " + "Broken")