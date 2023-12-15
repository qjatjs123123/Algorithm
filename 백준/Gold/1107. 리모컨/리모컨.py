import sys

for test_case in range(1):
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())
    length = len(str(n))
    brokenButton = list(map(int, sys.stdin.readline().split()))
    goodButton = []

    for button in range(0, 10):
        if button not in brokenButton:
            goodButton.append(button)

    ans = abs(100 - n)

    for num in range(1000001):
        string = str(num)
        flg = True

        for n1 in string:
            if int(n1) in brokenButton:
                flg = False
                break

        if not flg:
            continue

        tmp = abs(n - num) + len(string)
        ans= min(ans, tmp)

    print(ans)