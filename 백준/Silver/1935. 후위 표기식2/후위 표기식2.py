import sys
for test_case in range(1):
    n = int(sys.stdin.readline())
    string = sys.stdin.readline().rstrip()

    stack = []
    dp = {}
    for s in string:
        if s in ['*', '/', '+', '-']:
            second = stack.pop()
            first = stack.pop()

            if s == '*':
                stack.append(first*second)
            elif s == '/':
                stack.append(first / second)
            elif s == '+':
                stack.append(first + second)
            else:
                stack.append(first - second)
        else:
            if s not in dp:
                dp[s] = int(sys.stdin.readline())
            stack.append(dp[s])

    print('%.2f'%stack[0])