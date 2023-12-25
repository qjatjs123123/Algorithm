import sys

def solution():
    n = int(sys.stdin.readline())
    graph = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
    dp = {}
    direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
    for i in range(n**2):
        arr = list(map(int, sys.stdin.readline().split()))
        student, favorite = arr[0], arr[1:]
        dp[student] = favorite
        # 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
        max_love = 0
        max_arr = []
        for row in range(1, n + 1):
            for col in range(1, n + 1):
                if graph[row][col] == 0:
                    total = 0
                    for direct in direction:
                        new_row, new_col = row + direct[0], col + direct[1]
                        if 1 <= new_row < n + 1 and 1 <= new_col < n + 1 and graph[new_row][new_col] in favorite:
                            total += 1

                    if max_love < total:
                        max_love = total
                        max_arr = [[row, col]]

                    if max_love == total:
                        max_arr.append([row, col])

        if len(max_arr) == 1:
            graph[max_arr[0][0]][max_arr[0][1]] = student
            continue

        #1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
        max_blank = 0
        max_blank_arr = []
        for cur_row, cur_col in max_arr:
            total = 0
            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                if 1 <= new_row < n + 1 and 1 <= new_col < n + 1 and graph[new_row][new_col] == 0:
                    total += 1
            if max_blank < total:
                max_blank = total
                max_blank_arr = []
            if max_blank == total:
                max_blank_arr.append([cur_row, cur_col])

        if len(max_blank_arr) == 1:
            graph[max_blank_arr[0][0]][max_blank_arr[0][1]] = student
            continue

        #2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
        max_row = 9999
        max_row_arr = []
        for cur_row, cur_col in max_blank_arr:
            if max_row > cur_row:
                max_row = cur_row
                max_row_arr = []
            if max_row == cur_row:
                max_row_arr.append([cur_row, cur_col])

        if len(max_row_arr) == 1:
            graph[max_row_arr[0][0]][max_row_arr[0][1]] = student
            continue
        max_row_arr.sort()
        graph[max_row_arr[0][0]][max_row_arr[0][1]] = student

    arr = list(dp.keys())
    ans = 0
    for row in range(1, n + 1):
        for col in range(1, n + 1):
            total = 0
            for direct in direction:
                new_row, new_col = row + direct[0], col + direct[1]

                if 1 <= new_row < n + 1 and 1 <= new_col < n + 1 and graph[new_row][new_col] in dp[graph[row][col]]:
                    total += 1

            if total == 1:
                ans += 1
            elif total == 2:
                ans += 10
            elif total == 3:
                ans += 100
            elif total == 4:
                ans += 1000
    print(ans)
solution()