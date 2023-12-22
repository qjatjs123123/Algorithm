import sys

def solution():
    n = int(sys.stdin.readline())

    dp = []
    cnt = 0
    flg = False
    while True:
        tmp = []
        for i in range(10):
            if not dp:
                tmp.append([[i]])
                if cnt == n:
                    flg = True
                    print(i)
                    break
                cnt += 1
            else:
                prev = dp[-1]
                t = []
                for j in range(i):
                    idx_arr = prev[j]
                    for k in idx_arr:
                        t.append([i] + k)
                        if cnt == n:
                            flg = True
                            print(''.join(map(str, ([i] + k))))
                        cnt += 1
                tmp.append(t)
        dp.append(tmp)

        if flg or cnt == 1023:
            break
    if not flg:
        print(-1)
solution()