import sys
import copy
for test_case in range(1):
    n, m = map(int, sys.stdin.readline().split())

    string = list(sys.stdin.readline().split())
    ans = []
    tmp = []
    visited = [False for i in range(m)]
    mo_list = ['a', 'e', 'i', 'o', 'u']
    def backtracking():
        if len(tmp) == n:
            ja, mo = 0, 0

            for alpha in tmp:
                if alpha in mo_list:
                    ja += 1
                else:
                    mo += 1

            if ja >= 1 and mo >= 2:
                ans.append(copy.deepcopy(tmp))

            return

        for i in range(m):
            if not tmp:
                tmp.append(string[i])
                visited[i] = True
                backtracking()
                visited[i] = False
                tmp.pop()
                continue

            if ord(tmp[-1]) < ord(string[i]) and not visited[i]:
                tmp.append(string[i])
                visited[i] = True
                backtracking()
                visited[i] = False
                tmp.pop()

    backtracking()
    ans.sort()
    for a in ans:
        print(''.join(a))