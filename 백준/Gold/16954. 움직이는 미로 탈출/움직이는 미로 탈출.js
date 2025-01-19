const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split("\n");

let graph = input.map((data) => data.split(""));

const dx = [0, 0, 0, 1, 1, 1, -1, -1, -1];
const dy = [0, 1, -1, 0, 1, -1, 0, -1, 1];

const queue = [[7, 0]];

let ans = 1;
let cur_queue = [[7, 0]];
for (let time = 0; time < 8; time++) {
    const new_graph = move();
    cur_queue = bfs(cur_queue, new_graph);

    if (cur_queue.length === 0) {
        ans = 0;
        break;
    }

    graph = new_graph;

}

console.log(ans);

function bfs(cur_queue, new_graph) {
    const new_queue = []
    const visited = Array.from(new Array(8), () => new Array(8).fill(false));

    while (cur_queue.length !== 0) {
        const pos_arr = cur_queue.shift();

        for (let i = 0; i < 9; i++) {
            const new_row = pos_arr[0] + dy[i];
            const new_col = pos_arr[1] + dx[i];

            if (new_row < 0 || new_row == 8 || new_col < 0 || new_col == 8) continue;
            if (visited[new_row][new_col]) continue;
            if (graph[new_row][new_col] === '#') continue;
            if (new_graph[new_row][new_col] === '#') continue;
            
            new_queue.push([new_row, new_col]);
            visited[new_row][new_col] = true;
        }
    }
    
    return new_queue;
}

function move() {
    const new_graph = graph.map(arr => [...arr]);


    for (let row = 6; row >= 0; row--) {
        for (let col = 0; col < 8; col++) {
            new_graph[row + 1][col] = new_graph[row][col];
        }
    }

    for (let col = 0; col < 8; col++) new_graph[0][col] = '.';

    return new_graph;

}
