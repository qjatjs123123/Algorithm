from collections import deque
def solution(bridge_length, weight, truck_weights):
    answer = 0
    bridge = deque([0 for i in range(bridge_length)])
    truck_weights = deque(truck_weights)
    total = 0
    while truck_weights:
        total -= bridge.popleft()
        
        if truck_weights and total + truck_weights[0] <= weight:
            tmp = truck_weights.popleft()
            bridge.append(tmp)
            total += tmp
        
        else:
            bridge.append(0)
        answer += 1
    
    return answer + len(bridge)