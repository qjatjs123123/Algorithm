class Solution {
    static int[][] dp;
    
    public int solution(int[][] triangle) {
        dp = new int[501][501];
        
        for (int row = 0; row < 501; row++) {
            for (int col = 0; col < 501; col++) dp[row][col] = -1;
        }
        return dfs(0,0, triangle) + triangle[0][0];
    }
    
    static int dfs(int depth, int idx, int[][] triangle) {
        if (depth == triangle.length - 1) {
            return 0;
        }
        
        if (dp[depth][idx] != -1) return dp[depth][idx];
        
        int[] arr = triangle[depth];
        int result = 0;
        
        result = Math.max(result, dfs(depth + 1, idx, triangle) + triangle[depth + 1][idx]);
        result = Math.max(result, dfs(depth + 1, idx + 1, triangle) + triangle[depth + 1][idx + 1]);
        
        dp[depth][idx] = result;
        return result;
    }
}