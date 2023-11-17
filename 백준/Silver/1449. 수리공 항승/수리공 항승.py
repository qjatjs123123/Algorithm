for test_case in range(1):

    n, l = map(int, input().split())
    arr = list(map(int, input().split()))
    arr.sort()
    ans = 0

    while arr:
        start = arr[0] - 0.5
        end = start + l
        stack = []
        for num in arr:
            if start <= num <= end:
                stack.append(num)
            else:
                break
        for st in stack:
            arr.remove(st)
        ans += 1
    print(ans)