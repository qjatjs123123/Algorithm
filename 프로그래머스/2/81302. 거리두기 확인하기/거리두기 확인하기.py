from collections import deque
def solution(places):
    answer = []
    
    def bfs(row, col):
        q = deque()
        visited = [[False for _ in range(5)] for i in range(5)]
        visited[row][col] = True
        q.append((row, col, 0))
        direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
        flg = 1
        while q:
            cur_row, cur_col, cur_cnt = q.popleft()
            
            if cur_cnt == 3:
                continue
            
            for direct in direction:
                new_row, new_col = cur_row + direct[0], cur_col + direct[1]
                
                if 0 <= new_row < 5 and 0 <= new_col < 5 and not visited[new_row][new_col]:
                    visited[new_row][new_col] = True
                    if place[new_row][new_col] == 'P' and cur_cnt < 2:
                        flg = 0
                        break
                    if place[new_row][new_col] == 'O':
                        q.append((new_row,new_col,cur_cnt + 1))
                if not flg:
                    break
        return flg
        
    
    for place in places:
        result = 1
        for row in range(5):
            for col in range(5):
                if place[row][col] == 'P':
                    result = bfs(row, col)
                if not result:
                    break
            if not result:
                break
        answer.append(result)
        
    return answer