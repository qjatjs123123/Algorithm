import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Edge> graph[];
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
    
    static class Edge  {
        int no, dist;

        Edge(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }

    static class Route implements Comparable<Route>{
        int no, cnt, dist;

        Route(int no, int cnt, int dist) {
            this.no = no;
            this.cnt = cnt;
            this.dist = dist;
        }

        public int compareTo(Route o){
            if (this.dist < o.dist) return Integer.compare(this.dist, o.dist);
            return Integer.compare(this.cnt, o.cnt);
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt(); M = fs.nextInt();

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            int dist = fs.nextInt();

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        int cnt = 0;
        dijkstra();
    }

    static void dijkstra() {
        int[] distance = new int[N + 1];
        int[] path = new int[N + 1];

        for (int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;
        distance[1] = 0;
        
        PriorityQueue<Route> pq = new PriorityQueue<>();
        pq.add(new Route(1, 0, 0));

        while(!pq.isEmpty()) {
            Route route = pq.poll();

            if (route.dist > distance[route.no]) continue;

            ArrayList<Edge> list = graph[route.no];

            for(Edge edge: list) {
                int new_dist = route.dist + edge.dist;

                if (new_dist > distance[edge.no]) continue;
                path[edge.no] = route.no;
                distance[edge.no] = new_dist;
                pq.add(new Route(edge.no, route.cnt + 1, new_dist));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(N - 1).append("\n");
        
        for (int i = 2; i < N + 1; i++) {
            sb.append(i).append(" ").append(path[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

}