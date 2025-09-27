import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            
            int[] parents = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            int result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) parents[i] = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;

                Stack<Integer> stack = new Stack<>();
                HashMap<Integer, Boolean> map = new HashMap<>();

                stack.push(i);
                map.put(i, true);

                int new_node = i;
                int point = -1;
                boolean flg = false;
                
                while (true) {
                    new_node = parents[new_node];

                    if (visited[new_node]) {
                        flg = true;
                        break;
                    }
                    if (map.containsKey(new_node)) {
                        point = new_node;
                        break;
                    }
        
                    stack.push(new_node);
                    map.put(new_node, true);
                }

                boolean isCycle = true;
                
                while (!stack.isEmpty()) {
                    int top = stack.pop();

                    if (isCycle && point != -1) result++;
                    if (top == point) isCycle = false;

                    visited[top] = true;
                }

            } 

            sb.append(n - result).append("\n");
        }

        System.out.println(sb.toString());
    }
}