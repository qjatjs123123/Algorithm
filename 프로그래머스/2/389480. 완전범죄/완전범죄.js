function solution(info, n, m) {
    const map = new Map();
    
    function dfs(depth, A, B) {
        if (depth === info.length) {
            return 0;
        }
        const key = `${depth}, ${A}, ${B}`;
        if (map.has(key)) return map.get(key);
        
        const new_A = A + info[depth][0];
        
        let ans = 999_999_999;

        if (new_A < n) {
            ans = Math.min(ans, dfs(depth + 1, new_A, B) + info[depth][0]);
        }
        
        const new_B = B + info[depth][1];
        
        if (new_B < m) {
            ans = Math.min(ans, dfs(depth + 1, A, new_B));
        }
        
        map.set(key, ans);
        return ans;
    }
    const answer = dfs(0, 0, 0);
    return answer === 999_999_999 ? -1 : answer;
}

