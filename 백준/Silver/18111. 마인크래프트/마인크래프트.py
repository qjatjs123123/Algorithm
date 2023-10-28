import sys

def solution():
    for test_case in range(1):
        n, m, b = map(int, sys.stdin.readline().split())
        graph = []
        start = 0
        end = int(6.4 * 10**7)
    
        for _ in range(n):
            graph.append(list(map(int, sys.stdin.readline().split())))
    
        ans = []
        for earthHeight in range(256, -1, -1):
            inventory = b
            needs = 0
            time = 0
            for row in range(n):
                for col in range(m):
                    if graph[row][col] >= earthHeight:
                        inventory += graph[row][col]-earthHeight
                        time += 2*(graph[row][col]-earthHeight)
                    else:
                        needs += earthHeight - graph[row][col]
                        time += earthHeight - graph[row][col]
    
            if inventory >= needs:
                ans.append([time, earthHeight])
    
        ans.sort(key=lambda x:(x[0], -x[1]))
    
        print(' '.join(map(str,ans[0])))

solution()