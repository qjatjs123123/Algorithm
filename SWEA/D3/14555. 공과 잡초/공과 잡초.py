T = int(input())
for test_case in range(1, T+1):
    s = input()
    ans = s.count('(|') + s.count('|)') + s.count('()')
    print("#" + str(test_case) + " " + str(ans))