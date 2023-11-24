from collections import deque
import copy
def solution(queue1, queue2):
    answer = -1
    init_queue1 = copy.deepcopy(queue1)
    init_queue2 = copy.deepcopy(queue2)
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    
    queue1_sum = sum(queue1)
    queue2_sum = sum(queue2)
    
    cnt = 0
    flg = True
    while True:     
        if queue1_sum == queue2_sum:
            break
        
        if queue1_sum > queue2_sum:
            tmp = queue1.popleft()
            queue2.append(tmp)
            queue1_sum -= tmp
            queue2_sum += tmp
        
        else:
            tmp = queue2.popleft()
            queue1.append(tmp)
            queue2_sum -= tmp
            queue1_sum += tmp
        cnt += 1
        # if list(queue1) == init_queue1 or list(queue1) == init_queue2:
        #     flg = False
        #     break
        if cnt > 1000000:
            flg = False
            break
    
    if not flg:
        return -1
        
    return cnt