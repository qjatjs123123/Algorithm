function solution(diffs, times, limit) {
    
    let start = 1;
    let end = Math.pow(10, 15);
    
    
    while  (start <= end) {
        let mid = parseInt((start + end) / 2);
        

        let cur_limit = 0;
        
        for (let i = 0; i < diffs.length; i++) {
            if (diffs[i] <= mid) cur_limit += times[i];
            else cur_limit += (diffs[i] - mid) * (times[i] + times[i - 1]) + times[i];
        }
        
        if (cur_limit <= limit) end = mid - 1;
        else start = mid + 1;
    }
    
    return start;
}