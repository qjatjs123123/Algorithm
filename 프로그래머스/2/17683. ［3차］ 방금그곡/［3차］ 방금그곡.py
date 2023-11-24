def solution(m, musicinfos):
    answer = '(None)'
    
    def getTime(start, end):
        start = start.split(":")
        end = end.split(":")
        
        return (int(end[0])*60 + int(end[1])) - (int(start[0])*60 + int(start[1]))
    
    def getTuneList(tune):
        stack = []
        
        for t in tune:
            if t == "#":
                tmp = stack.pop()
                stack.append(tmp + "#")
            else:
                stack.append(t)
        
        return stack
    
    def getTuneAll(time, tune):
        if time < len(tune):
            return tune[:time]
        
        p = time // len(tune)
        c =  time % len(tune)
        return tune*p + tune[:c]
        
    def getAnswer(timeTune):
        melody = getTuneList(m)
        
        for i in range(len(timeTune) - len(melody) + 1):
            flg = True
            for j in range(len(melody)):
                if melody[j] != timeTune[i + j]:
                    flg = False
                    break
            if flg:
                return True
            
        return False
        
        
    result = 0
    for music in musicinfos:
        music = music.split(',')
        start, end, title, tune = music[0], music[1], music[2], music[3]
        time = getTime(start, end)
        tune = getTuneList(tune)
        
        timeTune = getTuneAll(time, tune)
        
        if getAnswer(timeTune) and result < time:
            answer = title
            result = time

            
    
    return answer