import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C, N;
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][10001];
        int[][] graph = new int[N][2];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < 2; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(graph, (a, b) -> {
            if (a[1] == b[1]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        for (int row = 0; row <= N; row++) {
            for (int col = 1; col <= 10000; col++) dp[row][col] = 999_999_999;
        }
        
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= 10000; col++) {
                int count = graph[row - 1][1];
                int value = graph[row - 1][0];

                if (col >= count) {
                    dp[row][col] = Math.min(dp[row - 1][col], dp[row][col - count] + value);
                } else {
                    dp[row][col] = dp[row - 1][col];
                }
                
            }
        }
        
        int answer = dp[N][C];
        
        for (int col = C; col <= 10000; col++) answer = Math.min(answer, dp[N][col]);

        // System.out.println(Arrays.deepToString(graph));
        // for (int row = 0; row <= N; row++) {
        //     for (int col = 0; col <= C; col++) {
        //         System.out.print(dp[row][col] + " ");
        //     }
        //     System.out.println();
        // }
        
        System.out.println(answer);
    }
}