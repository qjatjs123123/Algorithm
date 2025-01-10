import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    static int K;
    static int dp[][];
    static int[] prefix;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        arr = new int[N];
        prefix = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i + 1] = prefix[i] + arr[i];
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        dp = new int[4][N + 1];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N + 1; j++) dp[i][j] = -1;
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int cnt, int idx) {
        if (idx >= N || cnt == 3) return 0;
        if (dp[cnt][idx] != -1) return dp[cnt][idx];
        
        // o
        int total = prefix[ Math.min( idx + K, N ) ] - prefix[idx] + dfs(cnt + 1, idx + K);
        
        

        // x
        total = Math.max(total , dfs(cnt, idx + 1));
        dp[cnt][idx] = total;
        
        return total;
    }
}