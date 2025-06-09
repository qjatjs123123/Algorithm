import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] buildingHeight = new int[N];
        for (int i = 0; i < N; i++) buildingHeight[i] = Integer.parseInt(st.nextToken());

        int[] countArr = new int[N];
        int[] nodeArr = new int[N];
        for (int i = 0; i < N; i++) nodeArr[i] = 999_999_999;

        
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            int height = buildingHeight[i];

            if (stack.isEmpty()) {
                stack.push(new int[] {height, i});
                continue;
            }

            if (stack.peek()[0] > height) {
                nodeArr[i] = stack.peek()[1];
                countArr[i] = stack.size();
                stack.push(new int[] {height, i});
            } else {
                while (!stack.isEmpty() && stack.peek()[0] <= height) {
                    stack.pop();
                }

                if (stack.size() != 0) {
                    nodeArr[i] = stack.peek()[1];
                    countArr[i] = stack.size();
                }

                stack.push(new int[] {height , i});
            }
        }

        stack.clear();

        for (int i = N - 1; i >= 0; i--) {
            int height = buildingHeight[i];
            // System.out.println(stack.size());
            if (stack.isEmpty()) {
                stack.push(new int[] {height, i});
                continue;
            }

            if (stack.peek()[0] > height) {
                if (nodeArr[i] == 999_999_999) {
                    nodeArr[i] = stack.peek()[1];
                } else {
                    int base_len = Math.abs(nodeArr[i] - i);
                    int new_len = Math.abs(stack.peek()[1] - i);

                    if (base_len > new_len) nodeArr[i] = stack.peek()[1];
                }
                      
                countArr[i] += stack.size();
                stack.push(new int[] {height, i});
            } else {
                while (!stack.isEmpty() && stack.peek()[0] <= height) {
                    stack.pop();
                }

                if (stack.size() != 0) {
                    if (nodeArr[i] == 999_999_999) {
                        nodeArr[i] = stack.peek()[1];
                    } else {
                        int base_len = Math.abs(nodeArr[i] - i);
                        int new_len = Math.abs(stack.peek()[1] - i);
    
                        if (base_len > new_len) nodeArr[i] = stack.peek()[1];
                    }
                }
                countArr[i] += stack.size();
                stack.push(new int[] {height , i});
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if(nodeArr[i] == 999_999_999) sb.append(0).append("\n");
            else sb.append(countArr[i]).append(" ").append(nodeArr[i] + 1).append("\n");
        }
        System.out.println(sb.toString());
    }
}