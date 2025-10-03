import java.util.*;

class Solution {
    static Stack<Integer> stack = new Stack<>();
    static int[][] users;
    static int[] emoticons;
    
    static int[] answer = new int[] {0, 0};
    
    public int[] solution(int[][] u, int[] e) {
        users = u;
        emoticons = e;
        
        backtracking(0, emoticons.length);
        
        return answer;
    }
    
    static void calculate() {
        int cnt = 0;
        int total = 0;
        
        for (int[] user : users) {
            int user_percent = user[0];
            int user_money = user[1];
            
            int idx = -1;
            int temp = 0;
            for (int p : stack) {
                int emoticon_percent = p * 10;
                idx++;
                if (emoticon_percent < user_percent) continue;
                temp += emoticons[idx] - (emoticons[idx] * (emoticon_percent / 100F));
                
                if (temp >= user_money) {
                    temp = 0;
                    cnt++;
                    break;
                }
                
            }
            
            total += temp;
        }
        
        if (answer[0] < cnt) {
            answer[0] = cnt;
            answer[1] = total;
        }
        if (answer[0] == cnt) {
            answer[1] = Math.max(answer[1], total);
        }

    }
    
    static void backtracking(int depth, int N) {
        if (depth == N) {
            calculate();
            return;
        }
        
        for (int i = 1; i <= 4; i++) {
            stack.push(i);
            backtracking(depth + 1, N);
            stack.pop();
        }
    }
}