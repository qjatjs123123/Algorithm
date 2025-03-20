import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, m;
    static int[] graph;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    
        graph = new int[n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i] = Integer.parseInt(st.nextToken());
            dp[i] = -1;
        }

        
        System.out.println(dfs(0));
    }

    static int dfs(int start) {
        if (start >= graph.length) return 0;
        if (dp[start] != -1) return dp[start];
        int cur_length = 0;
        int blank = m;
        
        int result = Integer.MAX_VALUE;

        for (int i = start; i < graph.length; i++) {
            if (i == start) {
                blank -= graph[i];  // 첫 단어는 공백 없이 추가
            } else {
                blank -= (graph[i] + 1);  // 이후 단어들은 공백 포함
            }
        
            if (blank < 0) break;
            int cost = (i == n - 1) ? 0 : (blank * blank);
            result = Math.min(result, cost + dfs(i + 1));
            
            // result = Math.min(result, dfs(i + 1) + blank * blank);
        }

        
        dp[start] = result;
        return result;
    }
}