def solution(s):
    arr = s.split('},{')
    tmp = []
    for string in arr:
        ss = string.replace('{{', '').replace('}}', '')
        tmp.append(ss.split(','))
    tmp.sort(key = lambda x:(len(x)))
    answer = [0 for i in range(len(tmp))]
    dp = {}
    for new_arr in tmp:
        for num in new_arr:
            if num not in dp:
                answer[len(new_arr) - 1] = int(num)
                dp[num] = True
                break
    return answer