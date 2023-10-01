import sys
input = sys.stdin.readline
for test_case in range(1):
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())
    s = str(sys.stdin.readline())

    target = "IO"*n+"I"
    ans = 0
    i, j =0, 0
    cnt = 0
    while i<len(s):
        if s[i] == 'I' and s[i:i+3] == 'IOI':
            cnt += 1
            i += 2
            if cnt >= n:
                ans += 1
        else:
            cnt = 0
            i += 1


    print(ans)

