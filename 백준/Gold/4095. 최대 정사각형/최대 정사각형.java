import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if ( N == 0 && M == 0 ) break;
            
            int[][] dp = new int[N + 1][M + 1];

            for (int row = 1; row <= N; row++) {
                st = new StringTokenizer(br.readLine());

                for (int col = 1; col <= M; col++){
                    dp[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = 0;
            for (int row = 1; row <= N; row++) {
                for (int col = 1; col <= M; col++){
                    if (dp[row][col] == 0) continue;

                    int min_num = Math.min(dp[row - 1][col], dp[row - 1][col - 1]);
                    min_num = Math.min(min_num, dp[row][col - 1]);

                    dp[row][col] = min_num + 1;
                    answer = Math.max(answer, dp[row][col]);
                }
            }

            sb.append(answer).append("\n");
            
        }
        System.out.println(sb.toString());
    }
}