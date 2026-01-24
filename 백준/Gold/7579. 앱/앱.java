import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
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
    
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();

        int N = fs.nextInt(); int M = fs.nextInt();
        int[] memoryArr = new int[ N + 1 ];
        int[] costArr = new int[ N + 1 ];
        int[][] dp;
        int total = 0;
        
        for (int i = 1; i <= N; i++) {
            memoryArr[i] = fs.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            costArr[i] = fs.nextInt();
            total += costArr[i];
        }

        dp = new int[N + 1][total + 1];
        for (int row = 1; row <= N; row++) {
            int memory = memoryArr[row];
            int cost = costArr[row];

            for (int col = 0; col <= total; col++) {
                if (col < cost) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = Math.max(
                        dp[row - 1][col],
                        memory + dp[row - 1][col - cost]
                    );
                }
            }
        }

        for (int col = 0; col <= total; col++) {
            if (dp[N][col] >= M) {
                System.out.println(col);
                return;
            }
        }
    }
}