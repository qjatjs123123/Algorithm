import math
T = int(input())
for test_case in range(1, T+1):
    n, d = map(int, input().split())

    gap = d*2 + 1
    ans = math.ceil(n/gap)
    print("#" + str(test_case) + " " + str(ans))