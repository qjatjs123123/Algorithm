import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] memory;
    static int[] cost;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N];
        cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) memory[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) cost[i] = Integer.parseInt(st.nextToken());

        int[][] memo = new int[N][10001];
        memo[0][cost[0]] = memory[0];
        
        for (int r = 1; r < N; r++) {
            memo[r][cost[r]] = Math.max(memo[r][cost[r]], memory[r]);
            for (int c = 0; c <= 10000; c++) {
                if (memo[r-1][c] != 0) {
                    memo[r][c] = Math.max(memo[r][c], memo[r - 1][c]);
                    memo[r][c + cost[r]] = Math.max(memo[r][c + cost[r]], memo[r-1][c] + memory[r]);
                }
            }
        }

        for (int c = 0; c <= 10000; c++) {
            if (memo[N-1][c] >= M) {
                System.out.println(c);
                break;
            }
        }
    }

}