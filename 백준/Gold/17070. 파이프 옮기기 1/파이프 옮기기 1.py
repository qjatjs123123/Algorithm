import sys
sys.setrecursionlimit(10**6)

for test_case in range(1):
    n = int(sys.stdin.readline())
    graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

    #방향 0 => 오른쪽 1 => 아래쪽, 2=> 대각선

    direction = [[0, 2], [1, 2], [0, 1, 2]]
    move = [[0, 1], [1, 0], [1, 1]]
    blank = [[(0, 1)], [(1, 0)], [(0, 1), (1, 0), (1, 1)]]
    dp = {}
    def dfs(cur_row, cur_col, cur_direct):
        if (cur_row, cur_col, cur_direct) in dp:
            return dp[(cur_row, cur_col, cur_direct)]
        if cur_row == n - 1 and cur_col == n-1:
            return 1
        ans = 0
        for new_direct in direction[cur_direct]:
            new_row, new_col = cur_row + move[new_direct][0], cur_col + move[new_direct][1]

            flg = True
            for r, c in blank[new_direct]:
                check_row, check_col = cur_row + r, cur_col + c

                if 0 <= check_row < n and 0 <= check_col < n and graph[check_row][check_col] == 0:
                    continue
                flg = False
                break

            if flg:
                ans += dfs(new_row, new_col, new_direct)

        dp[(cur_row, cur_col, cur_direct)] = ans
        return ans


    print(dfs(0, 1, 0))