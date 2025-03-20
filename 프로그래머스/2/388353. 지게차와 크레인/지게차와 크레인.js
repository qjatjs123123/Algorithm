function solution(storage, requests) {
    var answer = 0;
    const graph = []
    const dy = [0, 0, 1, -1]
    const dx = [1, -1, 0, 0]
    
    for (let row = 0; row < storage.length + 2; row++) {
        const tmp = []
        
        for (let col = 0; col < storage[0].length + 2; col++) {
            if (row === 0 || row == storage.length + 1) tmp.push(0);
            else {
                if (col === 0 || col === storage[0].length + 1) tmp.push(0);
                else tmp.push(storage[row - 1][col - 1])
            }
            
        }
        graph.push(tmp)
    }
    
    requests.forEach((request) => {
        if (request.length === 1) {
            const queue = []
            const deque = []
            
            visited = []
            
            for (let row = 0; row < graph.length; row++) {
                let tmp = []
                for (let col = 0; col < graph[0].length; col++) {
                    tmp.push(false)
                }
                visited.push(tmp)
            }
            
            deque.push([0,0]);
            visited[0][0] = true
            
            while (true) {
                if (deque.length === 0) break;
                
                const cur_pos = deque.shift()
                
                for (let i = 0; i < 4; i++) {
                    const new_row = cur_pos[0] + dy[i];
                    const new_col = cur_pos[1] + dx[i];

                    if (new_row < 0 || new_row == graph.length) continue
                    if (new_col < 0 || new_col == graph[0].length) continue
                    if (visited[new_row][new_col]) continue
                    
                    if (graph[new_row][new_col] === 0) deque.push([new_row, new_col])
                    if (graph[new_row][new_col] === request[0]) queue.push([new_row, new_col])
                    visited[new_row][new_col] = true
                }
            }
            
            queue.forEach((arr) => graph[arr[0]][arr[1]] = 0)
        } else {
            
            for (let row = 0; row < graph.length; row++) {
                for (let col = 0; col < graph[0].length; col++) {
                    if (graph[row][col] === request[0]) graph[row][col] = 0;
                }
            }
        }

    })
    
    for (let row = 0; row < graph.length; row++) {
        for (let col = 0; col < graph[0].length; col++) {
            if (graph[row][col] !== 0) answer++
        }
    }
    
    return answer;
}