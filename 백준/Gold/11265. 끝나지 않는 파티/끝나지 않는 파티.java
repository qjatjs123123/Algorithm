import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N + 1][N + 1];
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());

                if (i == j) dp[i][j] = 0;
                else dp[i][j] = 1_000_000_001;
            }
        }

        for (int mid = 1; mid <= N; mid++) {
            for (int from = 1; from <= N; from++) {
                for (int to = 1; to <= N; to++) {
                    graph[from][to] = Math.min(graph[from][to], graph[from][mid] + graph[mid][to]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            if (graph[from][to] <= time) sb.append("Enjoy other party").append("\n");
            else sb.append("Stay here").append("\n");
        }

        System.out.println(sb.toString());
    }
}