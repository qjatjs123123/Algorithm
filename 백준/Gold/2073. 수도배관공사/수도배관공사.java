import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] dp = new int[D + 1];

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (D < L) continue;

            int end = D - L;

            for (int j = end ; j >= 1; j--) {
                if (dp[j] == 0) continue;
                dp[L + j] = Math.max(dp[L + j], Math.min(dp[j], C));
            }

            dp[L] = Math.max(dp[L], C);
        }

        System.out.println(dp[D]);
    }
}