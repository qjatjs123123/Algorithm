for test_case in range(1):
    x, y = map(int, input().split())
    def get_rate(x,y):
        return int((y*100 / x) )
    rate = get_rate(x, y)

    if rate >= 99:
        print(-1)
    else:
        start = 1
        end = x
        cnt = 1
        while start <= end:
            mid = (start+end) // 2
            prev_rate = get_rate(x+mid-1, y+mid-1)
            cur_rate = get_rate(x+mid, y+mid)
            if prev_rate == rate and cur_rate > rate:
                print(mid)
                break
            elif prev_rate > rate:
                end = mid - 1
            elif cur_rate <= rate:
                start = mid + 1
