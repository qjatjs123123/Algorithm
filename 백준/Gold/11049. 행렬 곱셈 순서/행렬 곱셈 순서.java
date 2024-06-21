import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
        }

        int dp[][] = new int[N][N];

        for (int gap = 1; gap < N; gap++) {
            for (int left = 0; left < N - gap; left++) {
                int right = left + gap;
                dp[left][right] = Integer.MAX_VALUE;

                for (int k = left; k < right; k++) {
                    int cost = dp[left][k] + dp[k+1][right] + graph[left][0] * graph[k][1] * graph[right][1];
                    if (cost < dp[left][right]) {
                        dp[left][right] = cost;
                    }
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}