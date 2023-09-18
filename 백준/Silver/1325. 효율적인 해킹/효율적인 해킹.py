import sys
from collections import deque

input = sys.stdin.readline
for test_case in range(1):

    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    for _ in range(m):
        a, b = map(int, input().split())
        graph[b].append(a)



    def bfs(num):
        visited = [0] * (n+1)
        q = deque()
        ans = 0
        q.append(num)
        visited[num] = 1
        while q:
            cur_num = q.popleft()


            ans += 1
            for tmp in graph[cur_num]:
                if not visited[tmp]:

                    q.append(tmp)
                    visited[tmp] = 1

        return ans


    answer = [0 for _ in range(n + 1)]
    for i in range(1, n + 1):
        answer[i] = bfs(i)
    for i in range(1, n + 1):
        if answer[i] == max(answer):
            print(i, end=' ')