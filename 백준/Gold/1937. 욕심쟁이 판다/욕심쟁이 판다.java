import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] graph;
    static int[][] dp;
    static boolean[][] visited;
    static int N;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        dp = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                visited[row][col] = true;
                answer = Math.max(answer, dfs(row, col, graph[row][col]));
                visited[row][col] = false;
            }
        }

        System.out.println(answer);
    }

    static int dfs(int row, int col, int init) {
        if (dp[row][col] != -1) {
            return dp[row][col];
        }
        
        int result = 1;
        for (int i = 0; i < 4; i++) {
            int new_row = row + dy[i];
            int new_col = col + dx[i];

            if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= N) continue;
            if (visited[new_row][new_col]) continue;
            if (graph[new_row][new_col] <= init) continue;

            visited[new_row][new_col] = true;
            result = Math.max(result, dfs(new_row, new_col, graph[new_row][new_col]) + 1);
            visited[new_row][new_col] = false;
        }
        
        dp[row][col] = result;
        return result;
    }
}