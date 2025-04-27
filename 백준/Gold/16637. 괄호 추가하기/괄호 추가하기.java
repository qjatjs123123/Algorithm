import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static String[] numArr;
    static String[] oprArr;
    static Stack<String> stack = new Stack<>();
    static int result = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        numArr = new String[N / 2 + 1];
        oprArr = new String[N / 2];
        
        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        for (int i = 0; i < N; i++) {
            int idx = i / 2;
            char c = str.charAt(i);
            
            if (i % 2 == 0) {
                numArr[idx] = Character.toString(c);
            } else {
                oprArr[idx] = Character.toString(c);
            }
        }

        dfs(0);

        System.out.println(result);
    }

    static void dfs(int idx) {
        int len = numArr.length;
         if(idx >= len) {
             calculate();
             return;
         }   

        
        // 괄호
        if (idx < len - 1) {

            int count = 5;
            stack.push("(");
            stack.push(numArr[idx]);
            stack.push(oprArr[idx]);
            stack.push(numArr[idx + 1]);
            stack.push(")");

            if (idx < len - 2) {
                stack.push(oprArr[idx + 1]);
                count++;
            }

            dfs(idx + 2);
            for (int j = 0; j < count; j++) stack.pop();
        }

        int count1 = 1;
        stack.push(numArr[idx]);

        if (idx < len - 1) {
            stack.push(oprArr[idx]);
            count1++;
        }
        

        dfs(idx + 1);
        for (int j = 0; j < count1; j++) stack.pop();
        
    }

    static int calculate() {
        Stack<String> new_stack = copyStack();

        Stack<String> tmp_stack = new Stack<>();

        
        while (!new_stack.isEmpty()) {
            String top = new_stack.pop();

            if (top.equals(")")) {
                Stack<String> tmp = new Stack<>();

                while(!new_stack.peek().equals("(")) {
                    tmp.push(new_stack.pop());
                }

                new_stack.pop();

                int total = Integer.parseInt(tmp.pop());
                String opr = tmp.pop();
                
                if (opr.equals("+")) total += Integer.parseInt(tmp.pop());
                else if(opr.equals("-")) total -= Integer.parseInt(tmp.pop());
                else total *= Integer.parseInt(tmp.pop());

                tmp_stack.push(Integer.toString(total));
            } else {
                tmp_stack.push(top);
            }
        }

        int answer = Integer.parseInt(tmp_stack.pop());

        while (!tmp_stack.isEmpty()) {
            String opr = tmp_stack.pop();

            if (opr.equals("+")) answer += Integer.parseInt(tmp_stack.pop());
            else if(opr.equals("-")) answer -= Integer.parseInt(tmp_stack.pop());
            else answer *= Integer.parseInt(tmp_stack.pop());
        }

        result = Math.max(result, answer);
        return answer;
    }

    static Stack<String> copyStack() {
        Stack<String> new_stack = new Stack<>();

        for (String ss : stack) new_stack.push(ss);
        return new_stack;
    }
}