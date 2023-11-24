from collections import deque
def solution(m, n, board):   
    tmp = []
    for b in board:
        tmp.append(list(b))
    cnt = 1
    board = tmp
    while True:
        stack = []
        cnt += 1
        for row in range(m-1):
            for col in range(n-1):
                me = board[row][col]
                right = board[row][col + 1]
                down = board[row + 1][col]
                side = board[row + 1][col + 1]
                
                if me != '_' and me == right == down == side:
                    stack.append((row, col))
                    stack.append((row + 1, col))
                    stack.append((row, col + 1))
                    stack.append((row + 1, col + 1))
        
        if not stack:
            break
        for r, c in stack:
            board[r][c] = '_'
        
        for col in range(n):
            q = []
            stack = []
            total = []
            for row in range(m):
                if board[row][col] == '_':
                    stack.append('_')
                else:
                    q.append(board[row][col])
            
            total = stack + q
            total.reverse()
            for row in range(m):
                board[row][col] = total.pop()
    answer = 0
    for row in range(m):
        for col in range(n):
            if board[row][col] == '_':
                answer += 1
    return answer
                