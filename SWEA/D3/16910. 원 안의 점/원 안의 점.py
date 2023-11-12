T = int(input())
for test_case in range(1, T+1):
    n = int(input())

    cnt = 0

    for row in range(1, n + 1):
        for col in range(1, n + 1):
            if row*row + col * col <= n*n:
                cnt += 1

    ans = cnt*4 + n*4 + 1
    print("#" + str(test_case) + " " + str(ans))