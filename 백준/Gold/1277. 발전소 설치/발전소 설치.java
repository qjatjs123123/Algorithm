import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, W;
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        double M = Double.parseDouble(st.nextToken());

        int[][] posArr = new int[N + 1][2];
        boolean[][] connected = new boolean[N + 1][N + 1];
        double[][] graph = new double[N + 1][N + 1];
        
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());

            posArr[row][0] = Integer.parseInt(st.nextToken());
            posArr[row][1] = Integer.parseInt(st.nextToken());
        }

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                double dist = getDistance(row, col, posArr);

                if (dist > M) graph[row][col] = 999_999_999;
                else graph[row][col] = dist;
            }
        }

        for (int row = 0; row < W; row++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = 0;
            graph[to][from] = 0;
        }
        
        double distance[] = new double[N + 1];
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(a[0], b[0]);
        });

        for (int col = 1; col <= N; col++) distance[col] = 999_999_999;
        pq.add(new double[] {0, 1});
        distance[1] = 0;

        while(!pq.isEmpty()) {
            double[] cur_arr = pq.poll();
            double cur_dist = cur_arr[0];
            int cur_node = (int) cur_arr[1];


            if (cur_dist > distance[cur_node]) continue;

            for (int new_node = 1; new_node <= N; new_node++) {
                double new_dist = cur_dist + graph[cur_node][new_node];
        
                if (distance[new_node] > new_dist) {
                    distance[new_node] = new_dist;
                    pq.add(new double[] {new_dist, new_node});
                }
            }
        }


        System.out.println((int)(distance[N] * 1000));
    }

    static double getDistance(int node1, int node2, int[][] posArr) {
        int rowF = posArr[node1][0];
        int colF = posArr[node1][1];
        int rowT = posArr[node2][0];
        int colT = posArr[node2][1];
        
        return Math.sqrt( (double)(rowT - rowF) * (rowT - rowF) + (double)(colT - colF) * (colT - colF) );
    }
}