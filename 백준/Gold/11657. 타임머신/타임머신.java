import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];

        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= N; col++) {
                if (row == col) {
                    graph[row][col] = 0;
                    continue;
                }
                
                graph[row][col] = Integer.MAX_VALUE;
            }
        }
        
        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A][B] = Math.min(C, graph[A][B]);
        }

        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    if (graph[start][mid] == Integer.MAX_VALUE || graph[mid][end] == Integer.MAX_VALUE) continue;

                    graph[start][end] = Math.min(graph[start][end], graph[start][mid] + graph[mid][end]);
                }
            }
        }

        boolean flg = false;
        boolean flg1 = false;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (graph[i][i] < 0) {
                flg = true;
                break;
            }
        }
        
        for (int i = 2; i <= N; i++) {
            if (graph[1][i] == Integer.MAX_VALUE)
                sb.append(-1).append("\n");
            else{
                sb.append(graph[1][i]).append("\n");
                flg1 = true;
            }
                
        }
        if (!flg1) {
            System.out.println(sb.toString());
        } else {
            if (flg) System.out.println(-1);
            else System.out.println(sb.toString());
        }
        
    }
}