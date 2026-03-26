import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

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

        int N = fs.nextInt(); 
        int M = fs.nextInt();

        int dp[][] = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                if (i == j) dp[i][j] = 0;
            }
        }

        for (int i = 0; i < M; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();

            dp[from][to] = 1;
        }

        for (int mid = 1; mid <= N; mid++) {
            for (int from = 1; from <= N; from++) {
                for (int to = 1; to <= N; to++) {
                    if (dp[from][mid] == Integer.MAX_VALUE || 
                       dp[mid][to] == Integer.MAX_VALUE) continue;

                    dp[from][to] = Math.min(dp[from][to], dp[from][mid] + dp[mid][to]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int T = fs.nextInt();

        for (int i = 0; i < T; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();

            if (dp[from][to] != Integer.MAX_VALUE) sb.append(-1);
            else {
                if (dp[to][from] != Integer.MAX_VALUE) sb.append(1);
                else sb.append(0);
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}