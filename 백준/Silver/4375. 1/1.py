import sys

for test_case in range(1):
    while True:
        try:
            num = int(sys.stdin.readline())
            s = "1"
            while True:
                if int(s) % num == 0:
                    print(len(s))
                    break
                s += "1"

        except:
            break