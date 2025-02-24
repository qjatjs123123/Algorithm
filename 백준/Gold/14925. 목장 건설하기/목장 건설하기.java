import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int M, N;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[M + 1][N + 1];

        for (int row = 1; row <= M; row++) {
            st = new StringTokenizer(br.readLine());
            
            for (int col = 1; col <= N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());

                if (graph[row][col] != 0) graph[row][col] = -1;
            }
        }

        int ans = 0;
        
        for (int row = 1; row <= M; row++) {
            for (int col = 1; col <= N; col++) {
                if (graph[row][col] != 0) continue;

                int left = graph[row][col - 1];
                int top = graph[row - 1][col];
                int leftTop = graph[row - 1][col - 1];

                if (left == -1 || top == -1 || leftTop == -1) {
                    graph[row][col] = 1;
                } else {
                    int tmp = Math.min(left, top);
                    tmp = Math.min(tmp, leftTop);
                    graph[row][col] = tmp + 1;
                }

                ans = Math.max(ans, graph[row][col]);
            }
        }

        System.out.println(ans);
    }
}