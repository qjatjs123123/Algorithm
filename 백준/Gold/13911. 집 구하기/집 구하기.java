import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    static ArrayList<Edge>[] graph;
    static int M, limit_M;
    static int S, limit_S;
    static FastScanner fs;
    static class Edge{
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

    }
    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        fs = new FastScanner();
        V = fs.nextInt(); E = fs.nextInt();
        int[] distance_M = new int[V + 1];
        int[] distance_S = new int[V + 1];
        graph = new ArrayList[V + 1];
        
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
            distance_M[i] = Integer.MAX_VALUE;
            distance_S[i] = Integer.MAX_VALUE;
        }
        
        for (int i = 0; i < E; i++) {
            int from = fs.nextInt(); 
            int to = fs.nextInt();
            int dist = fs.nextInt();

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        M = fs.nextInt();
        limit_M = fs.nextInt();

        dijkstra(distance_M, M);

        S = fs.nextInt();
        limit_S = fs.nextInt();
        dijkstra(distance_S, S);

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            if (distance_M[i] == 0 || distance_S[i] == 0) continue;
            if (distance_M[i] > limit_M || distance_S[i] > limit_S) continue;
            answer = Math.min(answer, distance_M[i] + distance_S[i]);
        }

        if (answer == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer);
    }

    static void dijkstra(int[] distance, int cnt) throws IOException{
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (int i = 0; i < cnt; i++) {
            int idx = fs.nextInt();
            distance[idx] = 0;
            pq.add(new int[] {0, idx});
        }

        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_dist = cur_arr[0];
            int cur_no = cur_arr[1];

            if (distance[cur_no] < cur_dist) continue;
            ArrayList<Edge> list = graph[cur_no];

            for (Edge edge: list) {
                int new_dist = cur_dist + edge.dist;

                if (distance[edge.to] <= new_dist) continue;
                distance[edge.to] = new_dist;
                pq.add(new int[] {new_dist, edge.to});
            }
        }
    }
}