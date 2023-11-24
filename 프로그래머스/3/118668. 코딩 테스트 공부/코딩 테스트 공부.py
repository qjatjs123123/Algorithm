def solution(alp, cop, problems):
    answer = 0
    row, col = 0, 0
    for problem in problems:
        row = max(row, problem[0])
        col = max(col, problem[1])
    
    dp = [[9999 for i in range(col + 1)] for _ in range(row + 1)]
    
    stack = []
    alp = min(alp, row)  # 둘중 하나라도 목표값을 넘어가면 안된다.
    cop = min(cop, col)
    dp[alp][cop] = 0
    
    
    for r in range(alp, row+1):
        for c in range(cop, col+1):
            
            dp[min(r+1, row)][c] = min(dp[min(r+1, row)][c], dp[r][c] + 1)
            dp[r][min(c+1, col)] = min(dp[r][min(c+1, col)], dp[r][c] + 1)
            for problem in problems:
                if r >= problem[0] and c >= problem[1]:
                    new_alp = min(r+problem[2], row)  # 둘중 하나라도 목표값을 넘어가면 안된다.
                    new_cop = min(c+problem[3], col)
                    dp[new_alp][new_cop] = min(dp[new_alp][new_cop], dp[r][c] + problem[4])
    print(dp)
    return dp[row][col]