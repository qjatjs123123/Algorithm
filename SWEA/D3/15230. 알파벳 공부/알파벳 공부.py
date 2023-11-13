T = int(input())
for test_case in range(1, T+1):
    s = input()
    a_num = 97
    z_num = 122
    ans = 0
    for ascii_num in range(97, 122+1):
        if ans == len(s):
            break
        if s[ans] == chr(ascii_num):
            ans += 1
            continue
        break

    print("#" + str(test_case) + " " + str(ans))