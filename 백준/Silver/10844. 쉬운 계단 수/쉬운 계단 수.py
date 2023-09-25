for test_case in range(1):
    n = int(input())
    ans = 0
    dp = {}

    def dfs(l, num):
        if (l, num) in dp:
            return dp[(l, num)]
        if l == 1:
            return 1
        cases = [num - 1, num + 1]
        result = 0
        for case in cases:
            if case < 0 or case > 9:
                continue
            result += dfs(l-1, case)
        dp[(l, num)] = result
        return result


    for i in range(1, 10):
        ans += dfs(n, i)
    print(ans% 1000000000)