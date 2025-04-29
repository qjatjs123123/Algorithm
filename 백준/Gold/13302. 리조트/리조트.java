import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static boolean[] visited;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        dp = new int[100][201];
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int idx = Integer.parseInt(st.nextToken());
                visited[idx - 1] = true;
            }
        }

        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 201; col++) dp[row][col] = -1;
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int idx, int coupon) {
        if (idx >= N) return 0;
        if (dp[idx][coupon] != -1) return dp[idx][coupon];

        int total = 999_999_999;
        if (visited[idx]) return dfs(idx + 1, coupon);
        
        total = Math.min(total, dfs(idx + 1, coupon) + 10_000);
        total = Math.min(total, dfs(idx + 3, coupon + 1) + 25_000);
        total = Math.min(total, dfs(idx + 5, coupon + 2) + 37_000);
        if (coupon >= 3) total = Math.min(total, dfs(idx + 1, coupon - 3));

        dp[idx][coupon] = total;
        return total;
    }
}