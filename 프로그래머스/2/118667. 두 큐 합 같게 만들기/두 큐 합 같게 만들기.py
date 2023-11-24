
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
def solution(queue1, queue2):
    small, big = [], []
    if sum(queue1) > sum(queue2):
        small, big = queue2.copy(), queue1.copy()
    else:
        small, big = queue1.copy(), queue2.copy()
    if sum(queue1) == sum(queue2):
        return 0
    ans = -1
    sum_small, sum_big = sum(small), sum(big)
    target = (sum_small + sum_big) / 2
    dp, total, l = {} , 0, len(small)
    for i in range(len(small)):
        total += small[i]
        dp[(total)] = i + 1
    total_big = 0
    dp[0] = 0
    for i in range(len(big) - 1):
        total_big += big[i]
        total += big[i]
        dp[total] = l + i + 1
        if sum_big - total_big > target:
            continue
        if target - (sum_big - total_big) in dp:
            ans = i + dp[target - (sum_big - total_big)] + 1
            break


    return ans