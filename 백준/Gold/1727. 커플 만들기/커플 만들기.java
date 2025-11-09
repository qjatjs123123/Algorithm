import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    
    
    static class FastScanner {
        BufferedReader br ;
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

    static int[][] dp;
    static int bigCnt, smallCnt;
    static int[] bigArr, smallArr;
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        int manCnt = fs.nextInt();
        int womanCnt = fs.nextInt();

        int[] manArr = new int[manCnt];
        int[] womanArr = new int[womanCnt];

        for (int i = 0; i < manCnt; i++) manArr[i] = fs.nextInt();
        for (int i = 0; i < womanCnt; i++) womanArr[i] = fs.nextInt();

        Arrays.sort(manArr);
        Arrays.sort(womanArr);

        int answer = 0;
        if (manCnt >= womanCnt) {
            bigCnt = manCnt;
            smallCnt = womanCnt;

            bigArr = manArr;
            smallArr = womanArr;
            dp = new int[bigCnt][smallCnt];
        }else {
            bigCnt = womanCnt;
            smallCnt = manCnt;

            bigArr = womanArr;
            smallArr = manArr;
            dp = new int[bigCnt][smallCnt];
        }

        for (int i = 0; i < bigCnt; i++) {
            for (int j = 0; j < smallCnt; j++) dp[i][j] = -1;
        }

        answer = dfs(0, 0);
        System.out.println(answer);
    }

    static int dfs(int s_idx, int b_idx) {
        if (s_idx >= smallCnt) return 0;
        if (dp[b_idx][s_idx] != -1) return dp[b_idx][s_idx];

        int total = 1_000_000_000;
        int last = bigCnt + s_idx - smallCnt;
        for (int i = b_idx; i <= last; i++) {
            int gap = Math.abs( smallArr[s_idx] - bigArr[i]); 

            total = Math.min(total, gap + dfs(s_idx + 1, i + 1));
        }

        dp[b_idx][s_idx] = total;
        return total;
    }
}