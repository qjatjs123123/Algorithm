import sys
from collections import deque
for test_case in range(1):
    tc = int(sys.stdin.readline())
    for _ in range(tc):
        command = sys.stdin.readline().rstrip()
        n = int(sys.stdin.readline())
        arr = deque(sys.stdin.readline().rstrip().replace('[', '').replace(']', '').split(','))
        if arr[0] == '':
            arr = deque([])
        direct = 0
        flg = True
        for cmd in command:
            if cmd == 'R':
                direct = (direct + 1) % 2
                continue
            if not arr:
                flg = False
                print('error')
                break
            if direct == 0:
                arr.popleft()
            else:
                arr.pop()
        if flg:
            if direct == 1:
                arr.reverse()

            tmp = list(arr)

            print('['+','.join(tmp)+']')