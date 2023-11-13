T = int(input())
for test_case in range(1, T+1):
    date = ["SAT", "FRI", "THU", "WED", "TUE", "MON", "SUN"]
    s = input()
    ans = date.index(s) + 1
    print("#" + str(test_case) + " " + str(ans))