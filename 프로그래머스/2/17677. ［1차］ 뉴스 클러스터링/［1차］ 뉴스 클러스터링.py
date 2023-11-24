def solution(str1, str2):
    dict1, dict2 = {}, {}
    
    for i in range(len(str1) - 1):
        string = str1[i: i+2]
        if string.isalpha():
            string = string.lower()
            
            if string in dict1:
                dict1[string] += 1
            else:
                dict1[string] = 1
    
    for i in range(len(str2) - 1):
        string = str2[i: i+2]
        if string.isalpha():
            string = string.lower()
            
            if string in dict2:
                dict2[string] += 1
            else:
                dict2[string] = 1
    
    intersection, union = 0, 0
    
    dict1_keys = list(dict1.keys())
    dict2_keys = list(dict2.keys())
    
    for key in dict1_keys:
        if key in dict2:
            intersection += min(dict1[key], dict2[key])
            
    dp = {}
    
    for key in dict1_keys:
        if key in dict2:
            union += max(dict1[key], dict2[key])
            dp[key] = True
        else:
            union += dict1[key]
            
    for key in dict2_keys:
        if key not in dp:
            union += dict2[key]
    
    if intersection == 0 and union == 0:
        return 65536
    
    answer = int((intersection / union)*65536)
    
    return answer