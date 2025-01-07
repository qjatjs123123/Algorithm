import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[][] arr;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K + 1][2];
        dp = new int[K + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int important = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            arr[i][0] = important;
            arr[i][1] = time;
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int l, int cur_time) {
        if (cur_time > N) return -999_999_999;
        if (l == K) return 0;
        if (dp[l][cur_time] != 0) return dp[l][cur_time];

        int max_num = Math.max(dfs(l + 1, cur_time),
                              dfs(l + 1, cur_time + arr[l][1]) + arr[l][0] );

        dp[l][cur_time] = max_num;
        return max_num;
    }
}