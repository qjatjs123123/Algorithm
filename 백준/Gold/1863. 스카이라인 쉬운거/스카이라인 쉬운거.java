import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Stack<Integer> stack = new Stack<>();
        int[][] posArr = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            posArr[i][0] = x;
            posArr[i][1] = y;
        }

        int answer = 0;
        for (int[] pos : posArr) {
            int y = pos[1];

            if (y == 0) {
                answer += stack.size();
                stack.clear();
                continue;
            }

            if (stack.isEmpty()) {
                stack.push(y);
                continue;
            }
            
            if (stack.peek() >= y) {
                while (!stack.isEmpty()) {
                    int top = stack.pop();

                    if (top > y) {
                        answer++;
                    } else if (top == y) {
                        continue;
                    } else {
                        stack.push(top);
                        break;
                    }
                }
            }

            stack.push(y);
        }

        answer += stack.size();

        System.out.println(answer);
    }
}