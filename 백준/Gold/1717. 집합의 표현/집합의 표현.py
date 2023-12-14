import sys
sys.setrecursionlimit(500000000)
n, m = map(int, sys.stdin.readline().split())

check = [i for i in range(n + 1)]

def find(k):
    if check[k] != k:
        check[k] = find(check[k])

    return check[k]


for _ in range(m):

    sig, a, b = map(int, sys.stdin.readline().split())


    if sig == 0:
        if find(a) > find(b):
            check[check[a]] = check[b]
        elif check[b] > check[a]:
            check[check[b]] = check[a]



    else:

        if find(a) != find(b):
            print("NO")
        else:
            print("YES")