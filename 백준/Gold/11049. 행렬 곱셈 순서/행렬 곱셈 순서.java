import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[][] graph;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N][2];
        dp = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            graph[i][0] = left;
            graph[i][1] = right;
            
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) dp[row][col] = -1;
        }

        System.out.println(dfs(0, N - 1));
    }

    static int dfs(int left, int right) {
        if (left == right) return 0;
        if (dp[left][right] != -1) return dp[left][right]; 
        
        int total = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            int tmp = graph[left][0] * graph[i][1] * graph[right][1];
            total = Math.min(total, dfs(left, i) + dfs(i + 1, right) + tmp);
        } 

        dp[left][right] = total;
        return total;
    }
}