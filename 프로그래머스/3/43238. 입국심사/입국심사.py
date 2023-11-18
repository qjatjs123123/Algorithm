import sys
def solution(n, times):
    answer = 0
    start = 1
    end = sys.maxsize
    
    while start <= end:
        mid = (start + end) // 2
        
        cnt = 0
        for time in times:
            cnt += mid // time
        
        if cnt >= n:
            end = mid - 1
        else:
            start = mid + 1

    return start