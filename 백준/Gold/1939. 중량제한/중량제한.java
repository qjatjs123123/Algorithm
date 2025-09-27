import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Edge>[] graph;
    static int start, end;
    static class Edge implements Comparable<Edge> {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(edge.dist, this.dist);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();

        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] visited = new int[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        visited[start] = 0;
        pq.add(new Edge(start, Integer.MAX_VALUE));

        while (!pq.isEmpty()) {
            Edge cur_edge = pq.poll();

            if (cur_edge.to == end) {
                System.out.println(cur_edge.dist);
                return;
            }
            
            ArrayList<Edge> list = graph[cur_edge.to];
            for (Edge new_edge : list) {
                int new_dist = Math.min(cur_edge.dist, new_edge.dist);

                if (visited[new_edge.to] >= new_dist) continue;
                pq.add(new Edge(new_edge.to, new_dist));
                visited[new_edge.to] = new_dist;
            }
        }
    }
}