import sys
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())
    arr1 = list(map(int, sys.stdin.readline().split()))
    arr1.sort()
    dp = {}
    ans = []
    arr = []
    def backtracking():

        if len(arr) == m:
            if ' '.join(map(str,arr)) not in dp:
                print(' '.join(map(str,arr)))
                dp[' '.join(map(str,arr))] = True
            return

        for i in arr1:
            if i >= arr[-1] :
                arr.append(i)
                backtracking()
                arr.pop()




    for num in arr1:
        arr.append(num)
        backtracking()
        arr.pop()