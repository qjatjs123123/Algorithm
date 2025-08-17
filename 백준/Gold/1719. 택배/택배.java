import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Edge>[] graph;
    static int[][] dp;
    static class Edge implements Comparable<Edge>{
        int no, dist;

        Edge(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }

        public int compareTo(Edge edge) {
            return Integer.compare(this.dist, edge.dist);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dp = new int[N + 1][N + 1];
        
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        for (int start = 1; start <= N; start++) dijkstra(start);

        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (row == col) sb.append("-");
                else sb.append(getNextNode(row, col));
                sb.append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dijkstra(int start) {
        int[] distance = new int[N + 1];
        for (int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;
        distance[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] {0, start});

        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_dist = cur_arr[0];
            int cur_no = cur_arr[1];

            if (distance[cur_no] < cur_dist) continue;

            ArrayList<Edge> list = graph[cur_no];
            for (Edge edge : list) {
                if (distance[edge.no] <= cur_dist + edge.dist) continue;

                distance[edge.no] = cur_dist + edge.dist;
                pq.add(new int[] {cur_dist + edge.dist, edge.no});
                dp[start][edge.no] = cur_no;
            }
        }
    }

    static int getNextNode(int start, int end) {
        int answer = end;

        
        while (true) {
            if (dp[start][answer] == start) break;

            answer = dp[start][answer];
        }

        return answer;
    }
}