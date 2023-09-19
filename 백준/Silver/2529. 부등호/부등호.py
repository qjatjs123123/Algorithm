import sys
from collections import deque

# t = int(sys.stdin.readline())
input = sys.stdin.readline
for test_case in range(1):
    k = int(sys.stdin.readline())
    arr = list(sys.stdin.readline().split())
    num = [i for i in range(0,10)]

    def maxNum():
        num = [i for i in range(0, 10)]
        ans = ''
        reverse_arr = []
        for giho in arr:
            cur_max = max(num)
            num.remove(cur_max)
            if giho == '>':
                if reverse_arr:
                    reverse_arr.append(cur_max)
                    reverse_arr.reverse()
                    ans += ''.join(map(str, reverse_arr))
                    reverse_arr = []
                else:
                    ans += str(cur_max)
            else:
                reverse_arr.append(cur_max)
        cur_max = max(num)
        if reverse_arr:
            reverse_arr.append(cur_max)
            reverse_arr.reverse()
            ans += ''.join(map(str, reverse_arr))
        else:
            ans += str(cur_max)
        return ans

    def minNum():
        num = [i for i in range(0, 10)]
        ans = ''
        reverse_arr = []
        for giho in arr:
            cur_min = min(num)
            num.remove(cur_min)
            if giho == '>':
                reverse_arr.append(cur_min)

            else:
                if reverse_arr:
                    reverse_arr.append(cur_min)
                    reverse_arr.reverse()
                    ans += ''.join(map(str, reverse_arr))
                    reverse_arr = []
                else:
                    ans += str(cur_min)
        cur_min = min(num)
        if reverse_arr:
            reverse_arr.append(cur_min)
            reverse_arr.reverse()
            ans += ''.join(map(str, reverse_arr))
        else:
            ans += str(cur_min)
        return ans

    print(maxNum())
    print(minNum())