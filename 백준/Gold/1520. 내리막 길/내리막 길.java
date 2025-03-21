import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int M, N;
    static int[][] graph;
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
    static long[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[M][N];
        dp = new long[M][N];
        
        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            
            for (int col = 0; col < N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
                dp[row][col] = -1;
            }
            
        }

        System.out.println(dfs(0, 0));
    }

    static long dfs(int cur_row, int cur_col) {
        if (cur_row == M - 1  && cur_col  == N - 1) {
            return 1;
        }

        if (dp[cur_row][cur_col] != -1) return dp[cur_row][cur_col];
        
        long result = 0;
        for (int i = 0; i < 4; i++) {
            int new_row = cur_row + dy[i];
            int new_col = cur_col + dx[i];

            if (new_row < 0 || new_row == M || new_col < 0 || new_col == N) continue;
            if (graph[new_row][new_col] >= graph[cur_row][cur_col]) continue;
            
            result += dfs(new_row, new_col);
        }

        dp[cur_row][cur_col] = result;
        return result;
    } 
}