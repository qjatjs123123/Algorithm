import sys
from collections import deque


def solution():
    tc = int(sys.stdin.readline())

    for _ in range(tc):
        n = int(sys.stdin.readline().rstrip())

        home_row, home_col = map(int, sys.stdin.readline().split())
        store = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
        festival_row, festival_col = map(int, sys.stdin.readline().split())

        q = deque()
        q.append((home_row,home_col))
        dp = {}
        flg = False
        while q:
            cur_row, cur_col = q.popleft()
            
            if abs(festival_row - cur_row) + abs(festival_col - cur_col) <= 1000:
                flg = True
                break
            
            for store_row, store_col in store:
                dist = abs(store_row - cur_row) + abs(store_col - cur_col)
                
                if (store_row, store_col) in dp:
                    continue
                
                if dist <= 1000:
                    q.append((store_row, store_col))
                    dp[(store_row, store_col)] = 1

        if flg:
            print('happy')
        else:
            print('sad')

solution()