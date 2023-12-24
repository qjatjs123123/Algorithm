import sys
from collections import deque


def solution():
    n = int(sys.stdin.readline())
    dp = {}

    for i in range(1, n + 1):
        dp[i] = int(sys.stdin.readline())
    ans = []
    def bfs(num):
        q = deque()
        top = []
        bottom = []

        visited = [False for _ in range(n + 1)]
        q.append(num)

        while q:
            cur_num = q.popleft()
            visited[cur_num] = True

            new_num = dp[cur_num]
            top.append(cur_num)
            bottom.append(new_num)
            if visited[new_num]:
                break
            q.append(new_num)
        top.sort()
        bottom.sort()
        if top == bottom:
            for num in top:
                chain[num] = True
                ans.append(num)

    chain = [False for _ in range(n + 1)]
    for i in range(1, n + 1):
        if not chain[i]:
            bfs(i)
    print(len(ans))
    ans.sort()
    for a in ans:
        print(a)
solution()