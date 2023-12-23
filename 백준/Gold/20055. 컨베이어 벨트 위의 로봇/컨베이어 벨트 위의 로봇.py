import sys
from collections import deque



def solution():
    n, k = map(int, sys.stdin.readline().split())
    belt = deque(map(int, sys.stdin.readline().split()))
    robot = deque([False for _ in range(n)])
    ans = 0
    while True:
        # 벨트 움직이기
        belt.appendleft(belt.pop())
        robot.appendleft(robot.pop())
        # 내리는 위치 로봇 내리기
        if robot[-1]:
            robot[-1] = False

        # 로봇 움직이기
        for i in range(n-2, -1, -1):
            if robot[i] and belt[i + 1] > 0 and not robot[i + 1]:
                belt[i + 1] -= 1
                robot[i] = False
                robot[i + 1] = True

        # 내리는 위치 로봇 내리기
        if robot[-1]:
            robot[-1] = False

        # 로봇 올리는 위치에 놓기
        if not robot[0] and belt[0] > 0:
            robot[0] = True
            belt[0] -= 1
        # 내구도 계산
        total = 0
        for b in belt:
            if b == 0:
                total += 1

        ans += 1
        if total >= k:
            print(ans)
            break

solution()