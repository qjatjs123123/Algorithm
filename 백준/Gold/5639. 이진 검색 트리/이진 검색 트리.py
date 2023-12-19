import sys
sys.setrecursionlimit(10**6)
for test_case in range(1):

    node = []

    while True:
        try:
            n = int(sys.stdin.readline().rstrip())
            node.append(n)
        except:
            break


    def postOrder(start, end):
        if start >= end:
            return

        mid = end

        for i in range(start + 1, end):
            if node[start] < node[i]:
                mid = i
                break

        postOrder(start + 1, mid)
        postOrder(mid, end)
        print(node[start])


    postOrder(0, len(node))