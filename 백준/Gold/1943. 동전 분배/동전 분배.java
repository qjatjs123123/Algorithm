import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int t = 0; t < 3; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int[][] coins = new int[n][2];
            boolean[] dp = new boolean[50_001];
            dp[0] = true;

            int total = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int coin = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());

                coins[i][0] = coin;
                coins[i][1] = cnt;

                total += coin * cnt;
            }

            for (int[] arr: coins) {
                int coin = arr[0];
                int cnt = arr[1];
                boolean[] temp = new boolean[50_001];
                for (int i = 0; i < 50_001; i++) temp[i] = dp[i];
                
                for (int i = 0; i < 50_001; i++) {
                    if (dp[i]) {
                        for (int j = 1; j <= cnt; j++) {
                            int tmp = i + coin * j;

                            if (tmp > 50_000) break;
                            temp[tmp] = true;
                        }
                    }
                }

               dp = temp;
            }

            if (total % 2 != 0) System.out.println(0);
            else {
                if (dp[total / 2]) System.out.println(1);
                else System.out.println(0);
            }
        }
    }
}