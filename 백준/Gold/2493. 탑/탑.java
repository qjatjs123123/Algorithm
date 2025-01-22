import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
    
        Stack<int[]> stack = new Stack();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                stack.push(new int[] {arr[i], i + 1});
                sb.append(i).append(" ");
                continue;
            }
            boolean isTallest = true;
            while (stack.size() != 0) {
                int[] top = stack.peek();
                int height = top[0];
                int idx = top[1];
                
                if (height > arr[i]) {
                    stack.push(new int[] {arr[i], i + 1});
                    sb.append(idx).append(" ");
                    isTallest = false;
                    break;
                } else{
                    stack.pop();
                }
            }

            if (isTallest) {
                sb.append(0).append(" ");
                stack.push(new int[] {arr[i], i + 1});
            }
        }

        System.out.println(sb.toString());
    }
}