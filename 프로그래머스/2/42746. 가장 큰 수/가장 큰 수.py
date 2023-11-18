def solution(numbers):
    
    tmp = []
    for num in numbers:
        tmp.append(str(num))
    tmp.sort(key=lambda x: (x[0], x * 3), reverse=True)

    return str(int(''.join(tmp)))