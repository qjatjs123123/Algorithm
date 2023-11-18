from heapq import heappush, heappop
def solution(prices):
    answer = [0 for i in range(len(prices))]
    q = []
    for i in range(len(prices)):
        if not q:
            heappush(q, (-prices[i], i))
        else:
            while True:
                if not q:
                    break
                max_num, max_idx = heappop(q)
                max_num = -max_num
                
                if max_num > prices[i]:
                    answer[max_idx] = i-max_idx
                    continue
                else:
                    heappush(q, (-max_num, max_idx))
                    break
            heappush(q, (-prices[i], i))
    
    while q:
        max_num, max_idx = heappop(q)
        answer[max_idx] = len(prices)-1-max_idx
                    
    return answer