import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int X, Y;
    static int[] dy = new int[] {0, 0, 0, 1, -1};
    static int[] dx = new int[] {0, 1, -1, 0, 0};
    static int[][] graph;
    static long[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        graph = new int[N][2];
        dp = new long[N][5];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < 5; col++) dp[row][col] = -1;
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

    static long dfs(int depth, int direct) {
        if (depth == N) {
            return 0;
        }
        if(dp[depth][direct] != -1) return dp[depth][direct];

        int prev_X, prev_Y;

        if (depth == 0) {
            prev_X = X;
            prev_Y = Y;
        } else {
            prev_X = graph[depth - 1][0] + dx[direct];
            prev_Y = graph[depth - 1][1] + dy[direct];
        }
        
        Long result = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            int new_X = graph[depth][0] + dx[i];
            int new_Y = graph[depth][1] + dy[i];

            int dist = Math.abs(new_X - prev_X) + Math.abs(new_Y - prev_Y);
            
            result = Math.min(result, dfs(depth + 1, i) + dist);
        }

        dp[depth][direct] = result;
        return result;
    }
}