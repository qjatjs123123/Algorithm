import sys
sys.setrecursionlimit(10**6)

def solution():
    h, w = map(int, sys.stdin.readline().split())
    graph = list(map(int, sys.stdin.readline().split()))
    ans = 0
    def left(max_idx):
        nonlocal ans
        left_max_idx = -1
        max_num = 0
        for idx in range(max_idx):
            if max_num < graph[idx]:
                max_num = graph[idx]
                left_max_idx = idx

        if left_max_idx == -1:
            return

        if left_max_idx == -1:
            return

        num = min(graph[left_max_idx], graph[max_idx])
        for idx in range(left_max_idx + 1, max_idx):
            ans += (num - graph[idx])

        left(left_max_idx)

    def right(max_idx):
        nonlocal ans
        right_max_idx = -1
        max_num = 0
        for idx in range(max_idx+1, len(graph)):
            if max_num < graph[idx]:
                max_num = graph[idx]
                right_max_idx = idx

        if right_max_idx == -1:
            return

        num = min(graph[right_max_idx], graph[max_idx])
        for idx in range(max_idx + 1, right_max_idx + 1):
            ans += (num - graph[idx])

        right(right_max_idx)

    max_idx = -1
    max_num = 0
    for idx in range(len(graph)):
        if max_num < graph[idx]:
            max_num = graph[idx]
            max_idx = idx

    left(max_idx)
    right(max_idx)

    print(ans)
solution()