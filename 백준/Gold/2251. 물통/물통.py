import sys
sys.setrecursionlimit(10**6)


def solution():
    a, b, c = map(int, sys.stdin.readline().split())

    ans = set()
    visited = [[[False for _ in range(a + 1)] for _ in range(b + 1)] for _ in range(c + 1)]
    def dfs(cur_a, cur_b, cur_c):
        nonlocal ans
        if cur_a == 0:
            ans.add(cur_c)
        if visited[cur_c][cur_b][cur_a]:
            return
        visited[cur_c][cur_b][cur_a] = True
        if cur_c != 0:
            new_a = min(cur_c, a - cur_a)
            new_b = min(cur_c, b - cur_b)
            if new_a != 0:
                dfs(cur_a + new_a, cur_b, cur_c - new_a)
            if new_b != 0:
                dfs(cur_a, cur_b + new_b, cur_c - new_b)
        if cur_b != 0:
            new_a = min(cur_b, a - cur_a)
            new_c = min(cur_b, c - cur_c)
            if new_a != 0:
                dfs(cur_a + new_a, cur_b - new_a, cur_c)
            if new_c != 0:
                dfs(cur_a, cur_b - new_c, cur_c + new_c)
        if cur_a != 0:
            new_b = min(cur_a, b - cur_b)
            new_c = min(cur_a, c - cur_c)
            if new_b != 0:
                dfs(cur_a - new_b, cur_b + new_b, cur_c)
            if new_c != 0:
                dfs(cur_a - new_c, cur_b, cur_c + new_c)
        visited[cur_c][cur_b][cur_a] = False


    dfs(0, 0, c)
    ans = list(ans)
    ans.sort()
    print(' '.join(map(str, ans)))



solution()