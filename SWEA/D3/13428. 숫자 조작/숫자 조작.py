T = int(input())
for test_case in range(1, T+1):
    s = input()

    q = []
    q.append(int(s))

    for i in range(len(s)):
        for j in range(i + 1, len(s)):
            tmp = list(s)
            t = tmp[i]
            tmp[i] = tmp[j]
            tmp[j] = t

            if tmp[0] != '0':
                q.append(int(''.join(tmp)))
    q.sort()
    print("#" + str(test_case) + " " + str(q[0]) + " " + str(q[-1]))