import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static boolean[] visited;
    static int[][] dp;
    
    static class Pos implements Comparable<Pos> {
        int idx, jumpCnt, dist;

        Pos(int idx, int jumpCnt, int dist) {
            this.idx = idx;
            this.jumpCnt = jumpCnt;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos edge) {
            return Integer.compare(this.jumpCnt, edge.jumpCnt);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        visited = new boolean[10_001];
        dp = new int[10_001][1000];

        for (int i = 0; i < 10_001; i++) {
            for (int j = 0; j < 1000; j++) dp[i][j] = -1;
        }
        
        visited[1] = true;
 

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            visited[num] = true;
        }
        dfs(2, 1);

        int answer = 99999;
        for (int i = 0 ; i < 1000; i++) {
            if (dp[2][i] == -1) continue;

            answer = Math.min(answer, dp[2][i]);
        }

        if (answer == 99999) System.out.println(-1);
        else System.out.println(answer);
    }

    static int dfs(int idx, int dist) {
        if (idx > 10_000 || visited[idx]) {
            return 99999;
        }
        if (dp[idx][dist] != -1) {
            return dp[idx][dist];
        }
        if (idx == N) {
            return 1;
        }
        // if (idx >=7 && idx <= 30) 
        //     System.out.println(idx + " " + dist + " " + dp[N]);
        
        int[] tmp = new int[] {dist + 1, dist, dist - 1};
        int result = 99999;
        for (int num : tmp) {
            if (num == 0) continue;
            result = Math.min(result, dfs(idx + num, num) + 1);
        }

        dp[idx][dist] = result;
        return result;
    }
}