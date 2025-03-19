function solution(points, routes) {
    let route_arr = [];
    let pos_arr = [];
    let isFinish = []
    const direction = [[0, 1], [0, -1], [1, 0], [-1, 0]];
    
    routes.forEach((arr, index) => {
        pos_arr.push(points[arr[0] - 1]);
        route_arr.push(1);
        isFinish.push(false);
    })
    let ans = 0;
    queue = []
    while (true) {
        let map = {};
        
        pos_arr.forEach((arr,index) => {
            if (isFinish[index]) return;
            const key = arr[0] * 10000 + arr[1];
            
            if (key in map) map[key] += 1;
            else map[key] = 1;
        })
        
        for(const key in map) {
            if (map[key] >= 2) ans++;
        }
        for (let i = 0; i < queue.length; i++) {
            isFinish[queue[i]] = true;
        }
        queue = []
        let flg = true;
        for (let i = 0; i < isFinish.length; i++) {
            if (!isFinish[i]) {
                flg = false;
                break;
            }
        }
        
        if (flg) break;
    
        let c= false;
        for (let i = 0; i < pos_arr.length; i++) {
            if (isFinish[i]) continue;

            let cur_pos = pos_arr[i];
            let route_idx = routes[i][route_arr[i]] - 1;
            let end_pos = points[route_idx];

            let new_pos = []
            let cur_dist = getDistance(cur_pos, end_pos);
        
            direction.forEach((direct, index) => {
                const new_row = cur_pos[0] + direct[0];
                const new_col = cur_pos[1] + direct[1];
                const new_arr = [new_row, new_col];

                const new_dist = getDistance(new_arr, end_pos);
                
                if (new_pos.length === 0) {
                    cur_dist = new_dist;
                    new_pos = new_arr;
                    return;
                }
                
                if (cur_dist >= new_dist) {
                    cur_dist = new_dist;
                    new_pos = new_arr;
                }
            })

            pos_arr[i] = new_pos;

            if (new_pos[0] === end_pos[0] && new_pos[1] === end_pos[1]) {
                route_arr[i] += 1;
                if (route_arr[i] === routes[0].length) {
                    c= true;
                    queue.push(i);
                }
            }
        }

        
    }
    
    
    return ans;
}

function getDistance(from, to) {
    return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
}