import sys

for test_case in range(1):
    arr = list(sys.stdin.readline().rstrip())
    arr.reverse()

    stack = []
    flg = True
    while arr:
        tmp = arr.pop()
        total = 0
        if tmp in ['(', '[']:
            stack.append(tmp)

        else:
            flg1 = False
            while stack:
                s = stack.pop()

                if s.isdigit():
                    total += int(s)
                    continue

                if tmp == ')' and s == '(':
                    if total == 0:
                        stack.append(str(2))
                    else:
                        stack.append(str(2*total))
                    flg1= True
                    break

                elif tmp == ']' and s == '[':
                    if total == 0:
                        stack.append(str(3))
                    else:
                        stack.append(str(3*total))
                    flg1 = True
                    break

                else:
                    flg1 = False
                    break
            flg = flg1
            if not flg:
                break

    ans = 0

    for num in stack:
        if num.isdigit():
            ans += int(num)
        else:
            flg = False
            break

    if not flg:
        print(0)
    else:
        print(ans)