import sys

for test_case in range(1):
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    visited = [False for i in range(n)]
    max_ans = -float("inf")
    def bactracking(idx, total):
        global visited
        global max_ans
        if idx == n:
            ans = 0
            for i in range(n-1):
                ans += abs(total[i] - total[i + 1])
            max_ans = max(max_ans, ans)
            return ans

        for i in range(n):
            if not visited[i]:
                visited[i] = True
                total[idx] = arr[i]
                bactracking(idx + 1, total)
                visited[i] = False
                total[idx] = 0
    bactracking(0, [0 for _ in range(n)])
    print(max_ans)