import sys
sys.setrecursionlimit(10**6)
import math

def solution():
    n = int(sys.stdin.readline())
    ans = []

    def prime(num):
        if num == 0 or num == 1:
            return False
        for i in range(2, int(math.sqrt(num)) + 1):
            if num % i == 0:
                return False
        return True


    def backtracking(num):
        nonlocal ans
        if len(num) == n:
            ans.append(num)
            return

        for i in range(10):
            target = num + str(i)
            if prime(int(target)):
                backtracking(target)
    backtracking('')

    for a in ans:
        print(a)
solution()