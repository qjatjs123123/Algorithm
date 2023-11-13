T = int(input())
for test_case in range(1, T+1):
    s = input()
    cnt = len(s)
    circle_cnt = s.count('o')
    rest = 15 - cnt

    if circle_cnt + rest >= 8:
        print("#" + str(test_case) + " " + "YES")
    else:
        print("#" + str(test_case) + " " + "NO")