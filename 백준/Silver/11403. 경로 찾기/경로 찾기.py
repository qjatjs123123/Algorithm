from collections import deque
import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    dp = {}
    for i in range(n):
        tmp = list(map(int, sys.stdin.readline().split()))
        for j in range(n):
            if tmp[j] == 0:
                continue
            if i not in dp:
                dp[i] = [j]
            else:
                dp[i] += [j]

    def bfs(num):
        ans = [0 for i in range(n)]

        q = deque()
        q.append(num)

        while q:
            cur_idx = q.popleft()
            if cur_idx not in dp:
                continue
            new_idx_arr = dp[cur_idx]
            for new_idx in new_idx_arr:
                if ans[new_idx] != 0:
                    continue
                ans[new_idx] = 1
                q.append(new_idx)
        return ans

    for i in range(n):
        print(*bfs(i))