T = int(input())
for test_case in range(1, T+1):
    num = int(input())
    arr = list(map(int, input().split()))
    case = []
    for i in range(7):
        if arr[i] == 1:
            case.append(i)
    ans = float("inf")
    arr = arr * 100000
    for n in case:
        cnt = 0
        for i in range(n, len(arr)):
            if arr[i] == 1:
                cnt += 1

            if cnt == num:
                ans = min(ans, i-n+1)
                break

    print("#" + str(test_case) + " " + str(ans))
