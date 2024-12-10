import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static int N;
    static int[][] graph;
    static int[][] memo;
    static int[][] direction = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        memo = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
                memo[row][col] = -1;
            }
        }
        
        int ans = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                ans = Math.max(ans, dp(row, col));
            }
        }

        System.out.println(ans);
    }

    static int dp (int row, int col) {
        if (memo[row][col] != -1) return memo[row][col];
        
        int total = 1;
        for (int[] direct : direction) {
            int new_row = row + direct[0];
            int new_col = col + direct[1];

            if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
            if (graph[row][col] >= graph[new_row][new_col]) continue;

            total = Math.max(total, dp(new_row, new_col) + 1);
        }

        memo[row][col] = total;
        return total;
    }
}