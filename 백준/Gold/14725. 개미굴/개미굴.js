const fs = require('fs');
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const N = parseInt(input[0]);

class Node {
    constructor(name) {
        this.name = name;
        this.child = new Map();
    }
}

const root = new Node("root1");

for (let i = 1; i <= N; i++) {
    const data = input[i].split(" ");
    const cnt = data[0];

    let cur_root = root;
    for (let j = 1; j <= cnt; j++) {
        const name = data[j];
        
        if (cur_root.child.has(name)) cur_root = cur_root.child.get(name);
        else {
            const new_node = new Node(name);
            cur_root.child.set(name, new_node);
            cur_root = new_node;         
        }       
    }
}

const str = "--";

function recursion(depth, node) {
    const keyArr = [...node.child.keys()].sort();
    if (depth >= 0)
        console.log(`${str.repeat(depth)}${node.name}`);
    if (keyArr.length === 0) return;
    
    for (let i = 0; i < keyArr.length; i++)
        recursion(depth + 1, node.child.get(keyArr[i]));
}

recursion(-1, root);
