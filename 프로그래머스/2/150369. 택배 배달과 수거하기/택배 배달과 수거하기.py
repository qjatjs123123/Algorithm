def solution(cap, n, deliveries, pickups):
    answer = 0
    while True:
        if not deliveries and not pickups:
            break
        for i in range(len(deliveries) - 1, -1, -1):
            if deliveries[i] == 0:
                deliveries.pop(i)
            else:
                break
        for i in range(len(pickups) - 1, -1, -1):
            if pickups[i] == 0:
                pickups.pop(i)
            else:
                break
        answer += 2* (max(len(deliveries), len(pickups)))
        cnt = cap
        
        for i in range(len(deliveries) - 1, -1, -1):
            if deliveries[i] <= cnt:
                cnt -= deliveries[i]
                deliveries.pop(i)
            else:
                deliveries[i] -= cnt
                break
        cnt = cap
        for i in range(len(pickups) - 1, -1, -1):
            if pickups[i] <= cnt:
                cnt -= pickups[i]
                pickups.pop(i)
            else:
                pickups[i] -= cnt
                break
    return answer
                
                