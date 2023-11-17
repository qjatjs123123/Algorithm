import sys
from collections import deque

def solution():
    t = int(sys.stdin.readline())

    for _ in range(t):
        n = int(sys.stdin.readline())
        arr = list(map(int, sys.stdin.readline().split()))

        visited = [False for i in range(n)]

        def bfs(node):

            q = deque()
            q.append((node))

            while q:
                cur_node = q.popleft()
                visited[cur_node] = True
                new_node = arr[cur_node] - 1
                if not visited[new_node]:
                    q.append((new_node))

        ans = 0
        for i in range(n):
            if not visited[i]:
                bfs(i)
                ans += 1

        print(ans)

solution()