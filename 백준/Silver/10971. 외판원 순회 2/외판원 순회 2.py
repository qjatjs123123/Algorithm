import sys
for test_case in range(1):
    n = int(sys.stdin.readline())

    arr = []
    for _ in range(n):
        arr.append(list(map(int, sys.stdin.readline().split())))

    ans = float("inf")
    def backtracking(start, total, city):
        global ans

        visited[start] = True

        if city == n-1:
            if arr[start][sta] != 0:
                total += arr[start][sta]
                ans = min(ans, total)
            return
        for i in range(n):
            if visited[i] or arr[start][i] == 0:
                continue

            visited[i] = True
            backtracking(i, total + arr[start][i], city + 1)
            visited[i] = False

    visited = [False for i in range(n)]

    for i in range(n):
        sta = i
        backtracking(i, 0, 0)


    print(ans)