for test_case in range(1):
    n, r, c = map(int, input().split())
    ans = 0
    flg = False
    def dfs(n,r1,c1,r2,c2):
        global flg
        global ans
        if n == 1:
            for row in range(r1,r2):
                for col in range(c1, c2):
                    if row == r and col == c:
                        print(ans)
                        flg=True
                    ans += 1
            return
        length = 2 ** n
        square = 1
        for i in range(r1,r2,length//2):
            for j in range(c1,c2,length//2):
                new_r1, new_c1 = i,j
                new_r2, new_c2 = i+length//2, j + length//2
                if new_r1 <= r < new_r2 and new_c1 <= c < new_c2:
                    ans += (length//2 * length//2) * (square-1)

                    dfs(n-1, new_r1, new_c1, new_r2, new_c2)

                square += 1

        return
    dfs(n, 0,0,2**n,2**n)