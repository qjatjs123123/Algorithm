import math
T = int(input())
for test_case in range(1, T+1):
    s1, s2 = map(str, input().split())
    gcd = math.gcd(len(s1), len(s2))
    cnt1, cnt2 =  len(s2) // gcd, len(s1) // gcd

    if (s1*cnt1 == s2*cnt2):
        print("#" + str(test_case) + " " + "yes")
    else:
        print("#" + str(test_case) + " " + "no")