from collections import deque


def solution(queue1, queue2):
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    q1s =sum(queue1)
    q2s =sum(queue2)
    if q1s+q2s % 2 == 1:
        return -1
    limit = len(queue1) + len(queue2)
    count = 0
    while q1s != q2s:
        if count >= limit:
            return -1
        while queue2 and q1s < q2s:
            tmp = queue2.popleft()
            queue1.append(tmp)
            count += 1
            q2s -= tmp
            q1s += tmp

        while queue1 and q1s > q2s:
            tmp = queue1.popleft()
            queue2.append(tmp)
            count += 1
            q1s -= tmp
            q2s += tmp
    return count