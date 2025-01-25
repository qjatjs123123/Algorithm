import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static char[] charArr;
    static char[] operation= {'+', '-', '*', '/', '(', ')'};
    static int[] level = {2, 2, 1, 1, 0, 0};
    static StringBuilder sb = new StringBuilder();
    static Stack<Character> stack = new Stack();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        String str = st.nextToken();
        charArr = new char[str.length()];

        for (int i = 0; i < charArr.length; i++) {
            charArr[i] = str.charAt(i);
        }

        

        for (char c : charArr) {
            if (!isOperation(c)) {
                sb.append(c);
                continue;
            }

            boolean isInsert = shouldInsertStack(c);
            
            if (isInsert) stack.push(c);
            else removeStack(c);
            
        }

        while(!stack.isEmpty()) sb.append(stack.pop());

        System.out.println(sb.toString());
        
    }

    static void removeStack(char opr) {

        if (opr == ')') {
            
            while (true) {
                char top = stack.pop();

                if (top == '(') break;
                sb.append(top);
            }
        } else{
            while (!stack.isEmpty()) {
                char top = stack.peek();
                
                if (top == '(' || getOperationLevel(top) > getOperationLevel(opr)) break;

                sb.append(stack.pop());
                
            }

            stack.push(opr);
        }
        
    }
    
    static boolean shouldInsertStack(char opr) {
        if (stack.size() == 0) return true;
        if (opr == '(') return true;
        if (opr == ')') return false;
        
        char top = stack.peek();
        if (getOperationLevel(top) > getOperationLevel(opr)) return true;
        return false;
    }

    static boolean isOperation(char c) {
        for (char opr : operation) if (opr == c) return true;
        return false;
    }

    static int getOperationLevel(char opr) {
        for (int i = 0; i < operation.length; i++) {
            if (operation[i] == opr) return level[i];
        }
        return -1;
    }
}