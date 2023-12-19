import sys

from collections import deque


def solution():
    for test_case in range(1):
        n, m = map(int, sys.stdin.readline().split())
    
        bridge = {}
    
        for _ in range(n + m):
            a, b = map(int, sys.stdin.readline().split())
            bridge[a] = b
    
        graph = [float('inf') for _ in range(101)]
    
        cur_pos = 1
    
        q = deque()
        q.append((cur_pos, 0))
    
        while q:
            cur_pos, cur_cnt = q.popleft()
            if cur_pos == 100:
                print(cur_cnt)
                break
            for i in range(1, 7):
                if cur_pos + i > 100:
                    continue
                new_pos = cur_pos + i
                while True:
                    if new_pos not in bridge:
                        break
                    new_pos = bridge[new_pos]
    
                if graph[new_pos] == float('inf'):
                    graph[new_pos] = cur_cnt + 1
                    q.append((new_pos, cur_cnt + 1))

solution()