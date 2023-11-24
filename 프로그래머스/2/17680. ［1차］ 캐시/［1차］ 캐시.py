from collections import deque
def solution(cacheSize, cities):
    answer = 0
    
    q = deque()
    for city in cities:
        city = city.lower()
        if city in q:
            q.remove(city)
            q.appendleft(city)
            answer += 1

        
        else:
            q.appendleft(city)
            if len(q) == cacheSize + 1:
                q.pop()     
            answer += 5

        
    return answer