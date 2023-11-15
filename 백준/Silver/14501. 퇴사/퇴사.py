import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = [[0,0]]
    for _ in range(n):
        arr.append(list(map(int, sys.stdin.readline().split())))

    ans = 0
    def backtracking(l, profit):
        global ans
        ans = max(ans, profit)
        for i in range(l, n+1):
            if i + arr[i][0]-1 <= n:
                backtracking(i + arr[i][0], profit + arr[i][1])


    backtracking(1, 0)
    print(ans)