import sys
sys.setrecursionlimit(10**6)
for test_case in range(1):
    n = int(sys.stdin.readline())

    def factorial(num):
        if num == 1 or num==0:
            return 1

        return num * factorial(num - 1)

    ans = 0
    two_cnt_max = n // 2
    for two_cnt in range(two_cnt_max + 1):
        one_cnt = n - two_cnt*2
        total = two_cnt + one_cnt

        tmp = (factorial(total) // factorial(one_cnt)) // factorial(two_cnt)
        ans += tmp
    print(ans%10007)
