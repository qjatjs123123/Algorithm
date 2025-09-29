import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    static ArrayList<Edge>[] graph;
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        int M, X;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int[] hamburgerArr = new int[M];
        int[] hamburgerDp = new int[V + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            hamburgerArr[i] = Integer.parseInt(st.nextToken());
        }

        int S, Y;
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int[] coffeeArr = new int[S];
        int[] coffeeDp = new int[V + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            coffeeArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= V; i++) {
            hamburgerDp[i] = Integer.MAX_VALUE;
            coffeeDp[i] = Integer.MAX_VALUE;
        }
        
        dijkstra(hamburgerDp, hamburgerArr);
        dijkstra(coffeeDp, coffeeArr);

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            if (hamburgerDp[i] == 0 || coffeeDp[i] == 0) continue;
            if (hamburgerDp[i] <= X && coffeeDp[i] <= Y) {
                answer = Math.min(answer, hamburgerDp[i] + coffeeDp[i]);
            }
        }
        
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static void dijkstra(int[] dp, int[] nodeArr) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (int node : nodeArr) {
            pq.add(new int[] {0, node});
            dp[node] = 0;  
        }

        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_dist = cur_arr[0], cur_node = cur_arr[1];

            if (dp[cur_node] < cur_dist) continue;

            ArrayList<Edge> list = graph[cur_node];
            for (Edge edge : list) {
                int new_dist = edge.dist + cur_dist;

                if (dp[edge.to] <= new_dist) continue;
                dp[edge.to] = new_dist;
                pq.add(new int[] {new_dist, edge.to});
            }
        }
    }
}