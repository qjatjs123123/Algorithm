import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] track = new int[1_000_000];
        for (int i = 0; i < 1_000_000; i++) track[i] = -1;

        
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[] {0, N});
        track[N] = -2;
        
        while(!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_dist = cur_arr[0];
            int cur_node = cur_arr[1];

            if (cur_node == K) {
                Stack<Integer> stack = new Stack();
                stack.push(K);
                int cur_num = track[cur_node];
                
                while(true) {
                    
                    if (cur_num == -2) break;

                    stack.push(cur_num);
                    cur_num = track[cur_num];
                }

                StringBuilder sb = new StringBuilder();
                sb.append(cur_dist).append("\n");

                while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");

                System.out.println(sb.toString());
                break;
            }
            
            int case1 = cur_node - 1;
            if (case1 >= 0 && track[case1] == -1) {
                track[case1] = cur_node;
                deque.add(new int[] {cur_dist + 1, case1});
            }

            int case2 = cur_node + 1;
            if (case2 < 1_000_000 && track[case2] == -1) {
                track[case2] = cur_node;
                deque.add(new int[] {cur_dist + 1, case2});
            }

            int case3 = cur_node * 2;
            if (case3 < 1_000_000 && track[case3] == -1) {
                track[case3] = cur_node;
                deque.add(new int[] {cur_dist + 1, case3});
            }
            
        }
        
    }
}