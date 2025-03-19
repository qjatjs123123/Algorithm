const dy = [1, -1, 0, 0];
const dx = [0, 0, 1, -1];
let row = 0;
let col = 0;
let visited = []

function solution(land) {
    var answer = 0;
    
    row = land.length;
    col = land[0].length;
    let num = 2;
    const count = {};
    
    for (let i = 0; i < row; i++) {
        let new_arr = []
        for (let j = 0; j < col; j++) new_arr.push(false);
        
        visited.push(new_arr);
    }
    
    for (let i = 0; i < row; i++) {
        for (let j = 0; j < col; j++) {
            if (land[i][j] == 0) continue;
            if (visited[i][j]) continue;
            
            const result = bfs(land, [i, j], num);
            count[num] = result;
            num++;
        }
    }
    
    let ans = 0;
    for (let i = 0; i < col; i++) {
        let total = 0;
        
        const set = new Set();
        for (let j = 0; j < row; j++) {
            if (land[j][i] != 0) {
                set.add(land[j][i]);
            }
        }
        
        for (const n of set) total += count[n];

        ans = Math.max(ans, total);
    }

    return ans;
}

function bfs(land, init_arr, idx) {
    let deque = []
    
    deque.push(init_arr);
    visited[init_arr[0]][init_arr[1]] = true;
    land[init_arr[0]][init_arr[1]] = idx;
    let count = 1;
    
    while (true) {
        if (deque.length === 0) break;
        
        const cur_arr = deque.shift();
        
        for (let i = 0; i < 4; i++) {
            const new_row = cur_arr[0] + dy[i];
            const new_col = cur_arr[1] + dx[i];
            
            if (new_row < 0 || new_row >= row || new_col < 0 || new_col >= col) continue;
            if (land[new_row][new_col] == 0) continue;
            if (visited[new_row][new_col]) continue;
            
            visited[new_row][new_col] = true;
            deque.push([new_row, new_col])
            land[new_row][new_col] = idx;
            count++;
        }
        
    }
    return count;
}