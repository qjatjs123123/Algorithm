for test_case in range(1):
    n = int(input())
    maps = []
    for _ in range(n):
        tmp = []
        for i in input():
            tmp.append(int(i))
        maps.append(tmp)

    def check(r1, c1, r2, c2):
        target = maps[r1][c1]
        flg = True
        for row in range(r1, r2):
            for col in range(c1, c2):
                if target != maps[row][col]:
                    flg = False
                    break
            if not flg:
                break
        if flg:
            return True
        else:
            return False

    def dfs(r1,c1,r2,c2, l):

        if l == 1:
            tmp = 0
            for row in range(r1, r2):
                for col in range(c1, c2):
                    tmp = maps[row][col]
            return str(tmp)
        ans = '' 
        if check(r1,c1,r2,c2):
            ans += str(maps[r1][c1])
        else:
            ans = "("
            r_mid = (r1 + r2) // 2
            c_mid = (c1 + c2) //2
            ans += dfs(r1, c1, r_mid, c_mid, l // 2)
            ans += dfs(r1, c_mid, r_mid, c2, l // 2)
            ans += dfs(r_mid, c1, r2, c_mid, l//2)
            ans += dfs(r_mid,c_mid, r2, c2, l//2)
            ans += ')'

        return ans

    print(dfs(0,0,n,n,n))