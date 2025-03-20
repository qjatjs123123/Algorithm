function solution(players, m, k) {
    var answer = 0;
    let server_arr = [players.length];
    
    for (let i = 0; i < players.length; i++) {
        const player = players[i]
        let new_server_arr = []
        
        for (let j = 0; j < server_arr.length; j++) {
            const time = i - server_arr[j];
            
            if (time < k) new_server_arr.push(server_arr[j]); 
        }
        server_arr = new_server_arr
        
        const max_player = m * new_server_arr.length - 1;
        
        if (player > max_player) {
            const diff = player - max_player
            let new_count = parseInt(diff / m) + 1;
            
            if (diff % m === 0) new_count--

            answer += new_count;
            for (let k = 0; k < new_count; k++) new_server_arr.push(i);
        }
        
    }
    
    return answer;
}