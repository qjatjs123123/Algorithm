import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] graph;
    static int[][][] dp;
    static int N, M, K;
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
        N = fs.nextInt(); M = fs.nextInt(); K = fs.nextInt();
        graph = new int[N][3];
        int total = 0;
        
        for (int i = 0; i < N; i++) {
            graph[i][0] = fs.nextInt();
            graph[i][1] = fs.nextInt();
            graph[i][2] = fs.nextInt();

            total += graph[i][2];
        }

        dp = new int[N + 1][total + 1][M + 2];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= total; j++) {
                for (int k = 0; k <= M + 1; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        
        for (int i = 1; i <= total; i++){

            int result = dfs(0, i, 0);

            if (result >= K) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    static int dfs(int no, int cur_priority, int cur_cpu) {
        if (no == N) {
            if (cur_cpu >= M)
                return 0;
            else
                return Integer.MIN_VALUE;
        }

        if (dp[no][cur_priority][cur_cpu] != -1) return dp[no][cur_priority][cur_cpu];

        
        int answer = dfs(no + 1, cur_priority, cur_cpu);
        int[] cur_arr = graph[no]; // cpu, memory, priority 
        int new_cpu = Math.min(M, cur_cpu + cur_arr[0]);
        if (cur_priority - cur_arr[2] >= 0) {
            answer = Math.max(
                dfs(no + 1, cur_priority - cur_arr[2], new_cpu) + cur_arr[1],
                answer
            );
        }

        dp[no][cur_priority][cur_cpu] = answer;
        return answer;
    }
}