
import sys
input = sys.stdin.readline
for test_case in range(1):
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())
    s = str(sys.stdin.readline())

    target = "IO"*n+"I"
    ans = 0
    for i in range(m - len(target) + 1):
        if s[i] == 'I' and s[i:i + len(target)] == target:
            ans += 1

    print(ans)

