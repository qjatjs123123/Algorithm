import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static int N;
    static int[] arr;
    static int[] prefix;
    static long[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        prefix = new int[N];
        dp = new long[4][N + 1];
        int total = 0;      
        int zero = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
            prefix[i] = total;

            if (arr[i] == 0) zero++;
        }

        int target = total / 4;
        
        for (int i = 1; i <= N; i++){
            int parent = prefix[i - 1] / target;
            int child = prefix[i - 1] % target;

            dp[1][i] = dp[1][i - 1];
            dp[2][i] = dp[2][i - 1];
            dp[3][i] = dp[3][i - 1];
            
            if (child == 0) {
                if (parent == 1) {
                    dp[parent][i] += 1;
                } else if(parent == 2) {
                    dp[2][i] = dp[2][i - 1] + dp[1][i];
                }  else if (parent == 3) {
                    dp[3][i] = dp[2][i] + dp[3][i - 1];
                }
            } 
        }

        if (zero == N) System.out.println( ((N - 1) * (N - 2) * (N - 3)) / 6  );
        else if (total % 4 != 0) System.out.println(0);
        else System.out.println(dp[3][N]);
        
    }

}