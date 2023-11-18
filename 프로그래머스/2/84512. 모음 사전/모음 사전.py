def solution(word):
    answer = 0
    cnt = 0
    tmp = []
    alpha = ['A', 'E', 'I', 'O', 'U']
    def backtracking(l):
        nonlocal cnt
        nonlocal answer
        cnt += 1
        if word == ''.join(tmp):
            answer = cnt - 1
            return
        if l == 5:
            return 
        for s in alpha:
            tmp.append(s)
            backtracking(l + 1)
            tmp.pop()
            
    backtracking(0)
    return answer
    
