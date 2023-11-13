T = int(input())
case = []
for test_case in range(1, T+1):
    a, b, c, d = map(int, input().split())

    arr = [0 for i in range(101)]

    for i in range(a, b):
        arr[i] = 1

    ans = 0
    for i in range(c, d):
        if arr[i] == 1:
            ans += 1
    case.append(ans)

for i in range(len(case)):
    print("#" + str(i+1) + " " + str(case[i]))