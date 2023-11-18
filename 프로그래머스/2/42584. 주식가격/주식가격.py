def solution(prices):
    answer = [0 for i in range(len(prices))]
    stack = []
    for i in range(len(prices)):
        if not stack:
            stack.append([prices[i], i])
        else:
            cur_max, cur_idx = stack[-1][0], stack[-1][1]
            
            if cur_max <= prices[i]:
                stack.append([prices[i], i])
            else:
                while True:
                    if not stack:
                        break
                    cur_max, cur_idx = stack[-1][0], stack[-1][1]
                    
                    if cur_max <= prices[i]:
                        break
                    
                    answer[cur_idx] = i-cur_idx
                    stack.pop()
                stack.append([prices[i], i])

    while stack:
        tmp = stack.pop()
        answer[tmp[1]] = len(prices) - 1 - tmp[1]
    return answer