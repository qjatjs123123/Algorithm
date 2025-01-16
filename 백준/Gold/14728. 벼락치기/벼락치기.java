import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, T;
    static int[][] arr;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        dp = new int[N + 1][100001];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            
            for (int col = 0; col < 2; col++) {
                arr[row][col] = Integer.parseInt(st.nextToken());    
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int idx, int cur_weight) {
        if (idx == N) return 0;
        if (dp[idx][cur_weight] != 0) return dp[idx][cur_weight];
        
        int new_weight = cur_weight + arr[idx][0];
        int total = 0;
        if (new_weight > T) total = dfs(idx + 1, cur_weight);
        else total = Math.max(dfs(idx + 1, new_weight) + arr[idx][1] , dfs(idx + 1, cur_weight));
        
        dp[idx][cur_weight] = total;
        return total;
    }
}