class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        int[][] dp = new int[2][n];
        
        if (tops[0] == 0) {
            dp[0][0] = 2;
            dp[1][0] = 3;
        } else {
            dp[0][0] = 3;
            dp[1][0] = 4;
        }
        // 1이면 prev 삼각형 보존, 0이면 하나 없는거
        for (int i = 1; i < n; i++) {
            if (tops[i] == 0) {
                dp[1][i] = (2*dp[1][i - 1] + dp[0][i - 1]) % 10007;
                dp[0][i] = (dp[1][i - 1] + dp[0][i - 1]) % 10007;
            } else {
                dp[1][i] = (3 * dp[1][i - 1] + dp[0][i - 1]) % 10007;
                dp[0][i] = (2 * dp[1][i - 1] + dp[0][i - 1]) % 10007;
            }
            
        }
        return dp[1][n - 1] % 10007;
        
    }
}