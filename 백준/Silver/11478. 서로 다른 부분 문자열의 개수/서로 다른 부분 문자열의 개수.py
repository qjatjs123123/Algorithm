import sys
for test_case in range(1):
    s = sys.stdin.readline().rstrip()
    ans = set()
    for length in range(1, len(s)+1):
        for i in range(len(s)-length + 1):
            ss = s[i:i+length]
            ans.add(ss)

    print(len(ans))