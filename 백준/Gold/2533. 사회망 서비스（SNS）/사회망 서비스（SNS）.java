import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[][] dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        
        for (int row = 0; row < N + 1; row++) {
            for (int col = 0; col < 2; col++) dp[row][col] = -1;
        }
        
        for (int i = 0 ; i <= N; i++) {
            ArrayList<Integer> new_list = new ArrayList<>();
            graph.add(new_list);
        }
        
        for (int row = 0; row < N - 1; row++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int min_num = Math.min(from, to);
            int max_num = Math.max(from, to);
            
            ArrayList<Integer> cur_list = graph.get(min_num);
            cur_list.add(max_num);
            cur_list = graph.get(max_num);
            cur_list.add(min_num);
        }

        System.out.println(Math.min(dfs(1, 0), dfs(1, 1)));
    }

    static int dfs(int node, int isAdaptor) {
        ArrayList<Integer> cur_list = graph.get(node);
        if (dp[node][isAdaptor] != -1) return dp[node][isAdaptor];
        
        if (cur_list.size() == 0) {
            if (isAdaptor == 0) return 0;
            else return 1;
        }
        
        int total = Integer.MAX_VALUE;
        visited[node] = true;
        if (isAdaptor == 1) {
            int hap = 1;
            
            for(int new_node : cur_list) {
                if (visited[new_node]) continue;
                hap += Math.min(dfs(new_node, 1), dfs(new_node, 0));
            }

            total = Math.min(total, hap);
        } else {
            int hap = 0;

            for(int new_node : cur_list) {
                if (visited[new_node]) continue;
                hap += dfs(new_node, 1);
            }

            total = Math.min(total, hap);
        }
        dp[node][isAdaptor] = total;
        visited[node] = false;
        return total;
    }
}