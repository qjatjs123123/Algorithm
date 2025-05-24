import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        char[] charArr = new char[str.length()];
        Stack<Character> stack = new Stack<>();

        for (int i = 0 ; i < str.length(); i++) charArr[i] = str.charAt(i);

        StringBuilder sb = new StringBuilder();
        
        for (char c : charArr) {
 
            if (Character.isUpperCase(c)) {
                sb.append(c);
         
            } else {
                if (stack.isEmpty() || c == '(') {
                    stack.push(c);
                    continue;
                } 
                
                if (c == ')') {
                    while (true) {
                        char top = stack.pop();
                        if (top == '(') break;

                        sb.append(top);
                    }
                } else {
                    while (!stack.isEmpty()) {
                        int top_level = getOprLevel(stack.peek());
                        int cur_level = getOprLevel(c);

                        if (top_level < cur_level) break;

                        sb.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) sb.append(stack.pop());

        System.out.println(sb.toString());
    }

    static int getOprLevel (char c) {
        if (c == '-' || c == '+') return 1;
        else if(c == '*' || c == '/') return 2;
        else return 0;
    }
}