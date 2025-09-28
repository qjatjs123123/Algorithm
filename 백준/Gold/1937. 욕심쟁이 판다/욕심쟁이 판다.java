import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n;
    static int[][] graph, dp;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        dp = new int[n][n];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < n; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
                dp[row][col] = -1;
            }
        }

        int answer = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                answer = Math.max(answer, dfs(row, col) + 1);
            }
        }

        System.out.println(answer);
    }

    static int dfs(int cur_row, int cur_col) {
        if (dp[cur_row][cur_col] != -1) return dp[cur_row][cur_col];

        int result = 0;
        for (int i = 0; i < 4; i++) {
            int new_row = cur_row + dy[i];
            int new_col = cur_col + dx[i];

            if (new_row < 0 || new_row == n || new_col < 0 || new_col == n) continue;
            if (graph[cur_row][cur_col] >= graph[new_row][new_col]) continue;

            result = Math.max(result, dfs(new_row, new_col) + 1);
        }

        dp[cur_row][cur_col] = result;
        return result;
    }
}