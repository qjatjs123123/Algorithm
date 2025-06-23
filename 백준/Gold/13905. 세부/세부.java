import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int s, e;
    static int[] dp;
    static ArrayList<Edge>[] graph;
    static class Edge {
        int to;
        int dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        pq.add(new int[] {Integer.MAX_VALUE, s});
        dp[s] = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_dist = cur_arr[0];
            int from = cur_arr[1];

            if (from == e) {
                System.out.println(cur_dist);
                return;
            }
            
            ArrayList<Edge> edgeList = graph[from];
            for (Edge edge : edgeList) {
                int new_dist = Math.min(edge.dist, cur_dist);

                if (dp[edge.to] >= new_dist) continue;

                dp[edge.to] = new_dist;
                pq.add(new int[] {new_dist, edge.to});
            }
        }

        System.out.println(0);
    }
}