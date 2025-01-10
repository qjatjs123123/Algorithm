import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, m;
    static int[][] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1][n + 1];

        for (int row = 0; row < n + 1; row++) {
            for (int col = 0; col < n + 1; col++) {
                if (row == col) graph[row][col] = 0;
                else graph[row][col] = 999_999;
            }
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (b == 1) {
                graph[u][v] = 0;
                graph[v][u] = 0;
            } else {
                graph[u][v] = 0;
                graph[v][u] = 1;
            }
        }

        for (int mid = 1; mid <= n; mid++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    graph[from][to] = Math.min(graph[from][to] , graph[from][mid] + graph[mid][to] );
                }
            }
        }

        st = new StringTokenizer(br.readLine()); 
        int k = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            sb.append(graph[from][to]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}