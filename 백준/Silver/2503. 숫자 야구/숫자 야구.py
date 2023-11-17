import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = []
    for _ in range(n):
        num, strike, ball = map(int, sys.stdin.readline().split())

        arr.append([num, strike, ball])

    ans = 0
    for first in range(1, 10):
        for second in range(1, 10):
            if second == first:
                continue
            for third in range(1, 10):
                if third == first or third == second:
                    continue
                flg = False
                for tmp in arr:
                    num, strike, ball = str(tmp[0]), tmp[1], tmp[2]

                    s, b = 0, 0
                    target = str(first) + str(second) + str(third)
                    for i in range(3):
                        if target[i] == num[i]:
                            s += 1
                            continue

                        if target[i] in num:
                            b += 1

                    if s != strike or b != ball:
                        flg = True


                if not flg:
                    ans += 1

    print(ans)