import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static int[][] down_dp, up_dp;
    static int[][] dy = new int[][] { {0, 1}, {0, -1} };
    static int[][] dx = new int[][] { {1, 0}, {1, 0} };
    
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() { 
            br = new BufferedReader(new InputStreamReader(System.in)); 
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
        
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        N = fs.nextInt();
        M = fs.nextInt();

        graph = new int[N + 2][M + 2];
        down_dp = new int[N + 2][M + 2];
        up_dp = new int[N + 2][M + 2];

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                graph[row][col] = fs.nextInt();
            }
        }

        for (int row = N; row >= 1; row--) {
            for (int col = 1; col <= M; col++) {
                if (row == N && col == 1) up_dp[row][col] = graph[row][col];
                else if (col == 1) up_dp[row][col] = up_dp[row + 1][col] + graph[row][col];
                else if (row == N) up_dp[row][col] = up_dp[row][col - 1] + graph[row][col];
                else up_dp[row][col] = Math.max( up_dp[row + 1][col], up_dp[row][col - 1] ) + graph[row][col];
            }
        }

        for (int row  = N; row >= 1; row--) {
            for (int col = M; col >= 1; col--) {
                if (row == N && col == M) down_dp[row][col] = graph[row][col];
                else if (row == N) down_dp[row][col] = down_dp[row][col + 1] + graph[row][col];
                else if (col == M) down_dp[row][col] = down_dp[row + 1][col] + graph[row][col];
                else down_dp[row][col] = Math.max( down_dp[row + 1][col], down_dp[row][col + 1] ) + graph[row][col];
            }
        }
        
        int answer = Integer.MIN_VALUE;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {             
                int total = up_dp[row][col] + down_dp[row][col];
       
                answer = Math.max(answer, total);
            }
        }

        System.out.println(answer);
    }

    
    static void display(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                sb.append(arr[row][col]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}