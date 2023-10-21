for test_case in range(1):
    s = input()
    prev = ''
    isMinus = False
    ans = 0
    for ss in s:
        if not ss.isdigit():
            if isMinus:
                ans -= int(prev)
            else:
                ans += int(prev)
            if ss == '-':
                isMinus = True
            prev = ''
        else:
            prev += ss

    if isMinus:
        ans -= int(prev)
    else:
        ans += int(prev)

    print(ans)