def solution(survey, choices):
    score = {
        1: 3,
        7: 3,
        2: 2,
        6: 2,
        3: 1,
        5: 1,
        4: 0
    }
    
    result = {
        'R': 0,
        'T': 0,
        'C': 0,
        'F': 0,
        'J': 0,
        'M': 0,
        'A': 0,
        'N': 0
    }
    for i in range(len(survey)):
        if choices[i] < 4:
            result[survey[i][0]] += score[choices[i]]
        else:
            result[survey[i][1]] += score[choices[i]]
    print(result)
            
    answer= ''
    for x, y in [['R', 'T'], ['C', 'F'], ['J', 'M'], ['A', 'N']]:
        if result[x] >= result[y]:
            answer += x
        elif result[x] < result[y]:
            answer += y
        
    return answer