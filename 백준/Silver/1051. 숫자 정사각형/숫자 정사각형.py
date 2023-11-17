for test_case in range(1):
    n, m = map(int, input().split())
    graph = []
    for _ in range(n):
        graph.append(input())

    for length in range(min(n,m)+1, 0, -1):
        flg1= False
        for row in range(n-length + 1):
            flg = False
            for col in range(m-length + 1):
                me = graph[row][col]
                right = graph[row][col+length-1]
                bottom = graph[row+length-1][col]
                side = graph[row+length-1][col+length-1]

                if me == right == bottom == side:
                    print(length*length)
                    flg = True
                    break
            if flg :
                flg1 = True
                break
        if flg1:
            break