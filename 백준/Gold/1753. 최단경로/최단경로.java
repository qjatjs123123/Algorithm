import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    static ArrayList<Edge>[] graph;
    
    static class Edge implements Comparable<Edge> {
        int node, dist;

        Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override 
        public int compareTo(Edge o){
            return Integer.compare(this.dist, o.dist);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
        }

        int[] distance = new int[V + 1];
        for (int i = 0; i < V + 1; i++) distance[i] = Integer.MAX_VALUE;
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (edge.dist > distance[edge.node]) continue;
            
            ArrayList<Edge> list = graph[edge.node];
            for (Edge new_edge : list) {
                if (distance[new_edge.node] >= new_edge.dist + edge.dist) {
                    distance[new_edge.node] = new_edge.dist + edge.dist;
                    pq.add(new Edge(new_edge.node, new_edge.dist + edge.dist));
                }
            }
            
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (int dist : distance) {
            if (idx == 0){
                idx++;
                continue;
            }
            if (dist == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(dist);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}