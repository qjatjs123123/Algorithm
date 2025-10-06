import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] graph = new int[1001][2];
    static int[][][] dp = new int[1001][16][16];
    static int n = 0;
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        while(true) {
            try {
                int whiteScore = fs.nextInt();
                int blackScore = fs.nextInt();
    
                graph[n][0] = whiteScore;
                graph[n][1] = blackScore;
                n++;
            } catch(Exception e) {
                break;
            }
        }


        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) dp[i][j][k] = -1;
            }
        }

        System.out.println(dfs(0, 15, 15));
    }

    static int dfs(int depth, int blackCnt, int whiteCnt) {
        if (depth == n){
            return 0;
        }
        
        if (dp[depth][blackCnt][whiteCnt] != -1) return dp[depth][blackCnt][whiteCnt];

        int answer = dfs(depth + 1, blackCnt, whiteCnt);
        if (blackCnt > 0) {
            answer = Math.max(answer, graph[depth][1] + dfs(depth + 1, blackCnt - 1, whiteCnt));
        }

        if (whiteCnt > 0) {
            answer = Math.max(answer, graph[depth][0] + dfs(depth + 1, blackCnt, whiteCnt - 1));
        }

        dp[depth][blackCnt][whiteCnt] = answer;
        return answer;
    }
}