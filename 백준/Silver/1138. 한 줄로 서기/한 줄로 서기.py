for test_case in range(1):
    n = int(input())
    arr = list(map(int, input().split()))
    ans = [-1 for i in range(n)]

    for i in range(len(arr)):
        num = arr[i]
        cnt = 0
        for j in range(len(ans)):
            if num == cnt and ans[j] == -1:
                ans[j] = i + 1
                break
            if ans[j] == -1:
                cnt += 1

    print(*ans)