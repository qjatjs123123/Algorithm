import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[][] graph;
    static int[][] dp;
    static int[] dy = new int[] {0, 0, 1, -1};
    static int[] dx = new int[] {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        dp = new int[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
                dp[row][col] = -1;
            }
        }

        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                answer = Math.max(answer, dfs(row, col));
            }
        }

        System.out.println(answer);
    }

    static int dfs(int row, int col) {
        if (dp[row][col] != -1) return dp[row][col];

        int result = 1;

        for (int i = 0; i < 4; i++) {
            int new_row = row + dy[i];
            int new_col = col + dx[i];

            if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
            if (graph[row][col] >= graph[new_row][new_col]) continue;
            
            result = Math.max(result, dfs(new_row, new_col) + 1);
        }

        dp[row][col] = result;
        return result;
    }
}