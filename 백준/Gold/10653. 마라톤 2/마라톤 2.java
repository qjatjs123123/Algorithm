import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[][] graph;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][2];
        dp = new int[N][K + 1];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col <= K; col++) dp[row][col] = -1;
        }
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[i][0] = x;
            graph[i][1] = y;
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int from, int k) {
        if (from >= N - 1) return 0;
        if (dp[from][k] != -1) return dp[from][k];
        
        int total = Integer.MAX_VALUE;

        for (int cnt = 0; cnt <= K - k; cnt++) {
            int to = from + cnt + 1;

            if (to == N) break;
            total = Math.min(total, dfs(to, k + cnt) + manhatan(from, to));
        }

        dp[from][k] = total;
        return total;
    }
    
    static int manhatan(int from, int to) {
        return Math.abs(graph[from][0] - graph[to][0]) + Math.abs(graph[from][1] - graph[to][1]);
    }
}