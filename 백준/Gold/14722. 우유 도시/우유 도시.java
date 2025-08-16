import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[][] graph;
    static int[][][] dp;
    static int[] dy = new int[] {1, 0};
    static int[] dx = new int[] {0, 1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        dp = new int[N][N][3];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());

                for (int k = 0; k < 3; k++) dp[row][col][k] = -1;
            }
        }

        int answer = 0;

        if (graph[0][0] == 0) {
            answer = Math.max(answer, dfs(0, 0, 0));
            answer = Math.max(answer, dfs(0, 0, 1) + 1);
        } else {
            answer = Math.max(answer ,dfs(0, 0, 0));
        }
        
        System.out.println(answer);
    }

    static int dfs(int row, int col, int color) {
        if (row == N - 1 && col == N - 1) {
            return 0;
        }

        if (dp[row][col][color] != -1) {
            return dp[row][col][color];
        }

        int result = 0;
        int target = (color + 1) % 3;
        for (int i = 0; i < 2; i++) {
            int new_row = row + dy[i];
            int new_col = col + dx[i];

            if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
            
            if (graph[new_row][new_col] == color) {
                result = Math.max(result, dfs(new_row, new_col, target) + 1);    
            }

            result = Math.max(result, dfs(new_row, new_col, color));
        }

        dp[row][col][color] = result;
        return result;
    }
}