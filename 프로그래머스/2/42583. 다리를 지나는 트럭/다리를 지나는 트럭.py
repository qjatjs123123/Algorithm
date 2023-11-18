from collections import deque
def solution(bridge_length, weight, truck_weights):
    answer = 0
    bridge = deque([0 for i in range(bridge_length)])
    truck_weights = deque(truck_weights)
    total = 0
    while bridge:
        total -= bridge.popleft()
        
        if truck_weights:
            if total + truck_weights[0] <= weight:
                num = truck_weights.popleft()
                total += num 
                bridge.append(num)
            else:
                bridge.append(0)
        answer += 1
        
    return answer