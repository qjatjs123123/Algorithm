from collections import deque
def solution(citations):
    dp = {}
    citations.sort(reverse = True)

    for cit in citations:
        if cit not in dp:
            dp[cit] = 1
        else:
            dp[cit] += 1
    
    answer = 0
    
    items = deque(dp.items())
    total = 0

    for index in range(len(citations), 0, -1):
        if items[0][0] >= index:
            while True:
                if not items or items[0][0] < index: 
                    break
                total += items[0][1]
                items.popleft()

        if index <= total and len(items) <= index:
            return index
    return 0