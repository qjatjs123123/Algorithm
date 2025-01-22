import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static boolean[] visited;
    static int NUM = 1_000_000;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        visited = new boolean[2_000_001];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int left = x - r + NUM;
            int right = x + r + NUM;

            pq.add(new int[] {left , 0, row});
            pq.add(new int[] {right, 1, row});
        }
        
        Stack<int[]> stack = new Stack<>();
        boolean ans = true;
        
        while(!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int num = cur_arr[0];
            int direct = cur_arr[1];
            int idx = cur_arr[2];
            
            if (direct == 0) {
                stack.push(cur_arr);
                continue;
            } else{
                int[] new_arr = stack.pop();
                int new_num = new_arr[0];
                int new_direct = new_arr[1];
                int new_idx = new_arr[2];
                
                if (new_idx != idx || visited[new_num]) {
                    ans = false;
                    break;
                }
                visited[new_num] = true;
            }
            
        }

        if (ans) System.out.println("YES");
        else System.out.println("NO");
    }
}