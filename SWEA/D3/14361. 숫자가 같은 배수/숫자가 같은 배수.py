T = int(input())
for test_case in range(1, T+1):
    q = []
    s = input()

    for N in range(2, 10):
        q.append(list(str(int(s) * N)))

    ispossible = False
    for case in q:
        for num in s:
            if num in case:
                case.remove(num)

        if not case:
            ispossible = True
            break

    if ispossible:
        print("#" + str(test_case) + " " + "possible")
    else:
        print("#" + str(test_case) + " " + "impossible")