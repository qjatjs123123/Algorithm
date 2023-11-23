from itertools import permutations
from collections import deque
def solution(expression):
    def convertString(string):
        tmp = ''
        result = []
        for ss in string:
            if ss in ['+', '-', '*']:
                result.append(int(tmp))
                result.append(ss)

                tmp = ''
                continue
            tmp += ss
        result.append(int(tmp))
        return result
    
    def calExpression(arr,exp):
        result = []
        q = deque(arr)
        while q:
            target = q.popleft()
            if target == exp:
                right = q.popleft()
                left = result.pop()
                if exp == '+':
                    result.append(left + right)
                elif exp == '-':
                    result.append(left - right)
                else:
                    result.append(left * right)
                continue
            result.append(target)
        return result
        
    prioritys = list(permutations(['-', '+', '*'], 3))
    answer = 0
    
    for priority in prioritys:
        arr = convertString(expression)
        for exp in priority:
            arr = calExpression(arr, exp)
        answer = max(answer, abs(arr[0]))

    return answer