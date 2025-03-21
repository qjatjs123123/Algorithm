import java.util.*;
import java.io.*;

class Solution {
    static int[][] graph, dp;
    static int dy[] = new int[] {0, 1};
    static int dx[] = new int[] {1, 0};
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        graph = new int[n][m];
        dp = new int[n][m];
               
        for (int[] arr : puddles) {
            graph[arr[1] - 1][arr[0] - 1] = 1;
        }
        
        for (int row = 0; row < n; row++) {
            for  (int col = 0; col < m; col++) dp[row][col] = -1;
        }
        
        answer = dfs(0, 0, m, n) % 1_000_000_007;
        
        return answer;
    }
    
    static int dfs(int cur_row, int cur_col, int m, int n) {
        if (cur_row == n - 1 && cur_col == m - 1) {
            return 1;
        }
        
        if (dp[cur_row][cur_col] != -1) return dp[cur_row][cur_col];
        
        int result = 0;
        
        for (int i = 0; i < 2; i++) {
            int new_row = cur_row + dy[i];
            int new_col = cur_col + dx[i];
            
            if (new_row == n || new_col == m) continue;
            if (graph[new_row][new_col] == 1) continue;
            
            result += dfs(new_row, new_col,m, n) % 1_000_000_007;
        }
        
        dp[cur_row][cur_col] = result % 1_000_000_007;
        
        return result;
    }
    
}