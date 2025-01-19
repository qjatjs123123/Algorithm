const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().split(' ');

const [ N, R, C ] = input.map(Number);

let ans = 0;

function recursion(start_row, start_col, end_row, end_col) {
    if (start_row === R && start_col === C) return;
    
    const length = end_row - start_row;
    const half = length / 2;
    
    //1 
    if (R >= start_row && R < start_row + half &&
       C >= start_col && C < start_col + half) {
        recursion(start_row, start_col, start_row + half, start_col + half);
    }
    else if (R >= start_row && R < start_row + half &&
            C >= start_col + half && C < end_col) {
        ans += (half * half);
        recursion(start_row, start_col + half, start_row + half, end_col);
    }
    else if (R >= start_row + half && R < end_row &&
            C >= start_col && C < start_col + half){
        ans += (half * half) * 2;
        recursion(start_row + half, start_col, end_row, start_col + half);    
    }
    else {
        ans += (half * half) * 3;
        recursion(start_row + half, start_col + half, end_row, end_col);   
    }
       
}

recursion(0, 0, 2**N, 2**N);

console.log(ans);