import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, K;
    static int[][] graph;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); 
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        dp = new int[N + 1][M + 1];
        visited = new boolean[N + 1];
        
        for (int row = 0; row <= N; row++) {
            ArrayList<Integer> new_list = new ArrayList<>();

            list.add(new_list);
        }

        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= M; col++) dp[row][col] = -1;
        }
        
        for (int row = 0; row < K; row++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (start > end) continue;
            ArrayList<Integer> cur_list = list.get(start);
            cur_list.add(end);
            
            graph[start][end] = Math.max(cost, graph[start][end]);
        }

        System.out.println(dfs(1, 1));
    }

    static int dfs(int cur_start, int count) {
        if (cur_start == N) {
            if (count > M) return -99999999;
            return 0;
        }
        if (count > M) return -99999999;
        
        if (dp[cur_start][count] != -1) return dp[cur_start][count];
        
        visited[cur_start] = true;
        ArrayList<Integer> new_list = list.get(cur_start);

        int total = -99999999;
        for (int new_start : new_list) {
            if (cur_start < new_start && !visited[new_start])
                total = Math.max(dfs(new_start, count + 1) + graph[cur_start][new_start], total);
        } 
        
        visited[cur_start] = false;
     
        dp[cur_start][count] = total;
        return total;
    }
}