import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static Deque<Integer> stack = new LinkedList<>();
    static boolean[] visited;
    static boolean flg;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        
        for (int i = 1; i <= N; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            graph.put(i, list);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            ArrayList<Integer> cur_list = graph.get(from);
            cur_list.add(to);
            cur_list = graph.get(to);
            cur_list.add(from);
        }

        int[] ans = new int[N + 1];

        for (int i = 1; i < N + 1; i++) ans[i] = -1;
        
        visited[1] = true;
        stack.add(1);
        dfs(1, -1);
        
        Deque<int[]> deque = new LinkedList<>();
        visited = new boolean[N + 1];
        
        for (int node : stack) {
            visited[node] = true;
            ans[node] = 0;
            deque.add(new int[] {node, 0});
        }
        
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_node = cur_arr[0];
            int cur_cnt = cur_arr[1];

            ArrayList<Integer> new_list = graph.get(cur_node);

            for(int new_node : new_list) {
                if (visited[new_node]) continue;
                visited[new_node] = true;
                ans[new_node] = cur_cnt + 1;
                deque.add(new int[] {new_node, cur_cnt + 1});
            }
            
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) sb.append(ans[i]).append(" ");

        System.out.println(sb.toString());
    }

    static void dfs(int cur_node, int prev) {
        if (flg) return;
        ArrayList<Integer> new_list = graph.get(cur_node);

        for (int new_node : new_list) {
            
            if (prev == new_node) continue;
            if (visited[new_node]) {

                while (!stack.isEmpty()) {
                    int left = stack.pollFirst();

                    if (left == new_node) {
                        stack.add(new_node);
                        break;
                    }
                }
                flg = true;
                break;
            }

            stack.add(new_node);
            visited[new_node] = true;
            dfs(new_node, cur_node);
            if (flg) break;
            stack.pollLast();
        }
    }
}