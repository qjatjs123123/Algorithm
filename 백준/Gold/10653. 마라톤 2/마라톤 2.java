import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[][] dp;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N][K + 1];
        graph = new int[N][2];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col <= K; col++) dp[row][col] = -1;
        }

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[row][0] = x;
            graph[row][1] = y;
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int from, int k) {
        if (from == N - 1) return 0;
        if (dp[from][k] != -1) return dp[from][k];

        int result = Integer.MAX_VALUE;
        int i = 0;
        for (int new_k = k; new_k <= K; new_k++) {
            int to = from + i + 1;

            if (to >= N) break;
            result = Math.min(result, dfs(to, new_k) + manhantan(from, to));
            i++;
        }

        dp[from][k] = result;
        return result;
    }

    static int manhantan(int from, int to) {
        return Math.abs(graph[from][0] - graph[to][0]) + Math.abs(graph[from][1] - graph[to][1]);
    }
}