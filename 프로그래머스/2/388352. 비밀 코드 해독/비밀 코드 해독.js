function solution(n, q, ans) {
    var answer = 0;
    
    let tmp = []
    function backtracking(depth, idx) {
        if (depth === 5) {
            let flg = true
            for (let i = 0; i < q.length; i++) {
                const cur_q = q[i];
                
                const count = cur_q.filter((num) => tmp.includes(num)).length;
                if (count !== ans[i]) {
                    flg = false;
                    break;
                }
            }
            
            if (flg) answer++;
            return;
        }
        
        for (let i = idx; i <= n; i++) {
            tmp.push(i)
            backtracking(depth + 1, i + 1)
            tmp.pop()
        }
    }
    
    backtracking(0, 1)
    return answer;
}