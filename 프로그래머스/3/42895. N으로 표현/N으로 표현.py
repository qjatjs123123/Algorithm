from collections import deque

def solution(N, number):
    answer = -1

    dp = []
    
    for cnt in range(1, 9):
        tmp = [int(str(N) * cnt)]
        for i in range(1, cnt):
            left, right = dp[i-1], dp[cnt - i - 1]
            
            for l in left:
                for r in right:
                    case = [l + r, l - r, l * r, l // r]
                    for c in case:
                        if c > 0:
                            tmp.append(c)
        dp.append(tmp)
        if number in tmp:
            return cnt
    

    return answer