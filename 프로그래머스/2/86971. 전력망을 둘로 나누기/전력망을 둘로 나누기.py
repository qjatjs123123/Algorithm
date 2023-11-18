from collections import deque
def solution(n, wires):
    answer = 9999999
    
    def bfs(node):
        q = deque()
        q.append(node)
        ans = 1
        visited[node] = True
        while q:
            cur_node = q.popleft()
            
            if cur_node not in dp:
                continue
            
            for new_node in dp[cur_node]:
                if not visited[new_node]:
                    ans += 1
                    visited[new_node] = True
                    q.append(new_node)
        
        return ans
    
    
    for i in range(len(wires)):
        dp = {}
        visited = [False for i in range(n + 1)]
        for j in range(len(wires)):
            if i == j:
                continue
            start, end = wires[j][0], wires[j][1]
        
            if start not in dp:
                dp[start] = [end]
            else:
                dp[start] += [end]

            if end not in dp:
                dp[end] = [start]
            else:
                dp[end] += [start]
        
        tmp = []
        for node in range(1, n + 1):
            if not visited[node]:
                tmp.append(bfs(node))
            
        answer = min(answer, abs(tmp[0] - tmp[1]))
            
            
    
    return answer