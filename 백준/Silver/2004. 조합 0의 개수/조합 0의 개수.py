import sys

for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    def getNum(n1, target):
        ans = 0
        num = n1
        while num <= target:
            ans += target // num
            num = num*n1
        return ans

    parent_two, parent_five = getNum(2, n) - getNum(2, n-m), getNum(5, n) - getNum(5, n-m)
    child_two, child_five = getNum(2, m), getNum(5, m)
    print(min((parent_two - child_two), (parent_five - child_five)))