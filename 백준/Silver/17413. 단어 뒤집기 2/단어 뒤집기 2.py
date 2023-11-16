import sys
for test_case in range(1):
    string = sys.stdin.readline().rstrip()
    ans = ''
    stack = []
    isflg = False

    for s in string:
        if s == '<':
            while stack:
                ans += stack.pop()
            isflg = True
            ans += "<"
            continue

        if s == '>':
            isflg = False
            ans += ">"
            continue
        if not isflg and s == ' ':
            while stack:
                ans += stack.pop()
            ans += s
            continue
        if isflg:
            ans += s

        else:
            stack.append(s)

    while stack:
        ans += stack.pop()
    print(ans)