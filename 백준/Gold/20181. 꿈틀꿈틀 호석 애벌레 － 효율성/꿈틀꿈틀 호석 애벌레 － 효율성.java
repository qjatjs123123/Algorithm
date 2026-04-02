import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[] graph;
    static long[] dp;
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt(); K = fs.nextInt();
        graph = new int[N];
        dp = new long[N];

        for (int i = 0; i < N; i++) {
            graph[i] = fs.nextInt();
            dp[i] = -1;
        }

        System.out.println(dfs(0));
    }

    static long dfs(int idx) {
        if (idx >= N) return 0;
        if (dp[idx] != -1) return dp[idx];
        

        long answer = 0;
        long tmp = 0;
        for (int i = idx; i < N; i++) {
            tmp += graph[i];
            if (tmp >= K)  {
                answer = Math.max(answer, dfs(i + 1) + (tmp - K));
                break;
            }
        }
        answer = Math.max(answer, dfs(idx + 1));
        dp[idx] = answer;
        return answer;
    }
}