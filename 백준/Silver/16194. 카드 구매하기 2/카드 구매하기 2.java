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
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        int N = fs.nextInt();
        int[] cardArr = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];


        for (int i = 1; i <= N; i++) {
            cardArr[i] = fs.nextInt();
        }

        for (int i = 1; i <= N; i++) {
            dp[1][i] = cardArr[1] + dp[1][i - 1];
        }

        for (int row = 2; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                int price = cardArr[row];

                if (row > col) {
                    dp[row][col] = dp[row - 1][col];
                } else {
                    dp[row][col] = Math.min(
                        dp[row - 1][col],
                        price + dp[row][col - row]
                    );
                }


            }
        }

        System.out.println(dp[N][N]);
    }
}