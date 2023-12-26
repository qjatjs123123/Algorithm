import sys
sys.setrecursionlimit(10**6)

def solution():
    n = int(sys.stdin.readline())
    graph = [[' ' for _ in range(n)] for _ in range(n)]

    def draw(start_row, start_col, end_row, end_col):
        if end_row - start_row == 1:
            graph[start_row][start_col] = '*'
            return
        length = (end_row - start_row) // 3
        total = 0
        for row in range(start_row, end_row, length):
            for col in range(start_col, end_col, length):
                total += 1
                if total == 5:
                    continue
                draw(row, col, row+length, col + length)
    draw(0, 0, n, n)

    for r in graph:
        print(''.join(r))
solution()