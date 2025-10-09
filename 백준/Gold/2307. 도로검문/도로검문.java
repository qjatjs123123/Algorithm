import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static Edge[] edgeArr;
    static ArrayList<Edge>[] graph;
    static int[] nodePath;
    static Edge[] edgePath;
    
    static class Edge{
        int to, dist;

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

        graph = new ArrayList[N + 1];
        edgeArr = new Edge[M * 2];
        nodePath = new int[N + 1];
        edgePath = new Edge[N + 1];

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            Edge e1 = new Edge(to, dist);
            Edge e2 = new Edge(from, dist);

            graph[to].add(e2);
            graph[from].add(e1);

            edgeArr[i * 2] = e1;
            edgeArr[i * 2 + 1] = e2;
        }

        int min = dijkstra(null);
        int dist = -1;
        
        ArrayList<Edge> list = shortestEdge();
        for (Edge edge : list) {
            int tmp = dijkstra(edge);
            if (tmp == Integer.MAX_VALUE) {
                dist = -1;
                break;
            }
            dist = Math.max(dist, tmp - min);
        }

        System.out.println(dist);

    }

    static ArrayList<Edge> shortestEdge() {
        ArrayList<Edge> result = new ArrayList<>();

        int node = N;

        while (true) {
            if (node == 1 || node == 0) break;
            result.add(edgePath[node]);

            node = nodePath[node];
        }

        return result;
    }
    
    static int dijkstra(Edge banEdge) {
        int[] distance = new int[N + 1];
        for (int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;

        distance[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], b[1]));
        pq.add(new int[] {1, 0});

        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_node = cur_arr[0];
            int cur_dist = cur_arr[1];

            if (cur_dist > distance[cur_node]) continue;

            ArrayList<Edge> list = graph[cur_node];

            for (Edge edge : list) {
                int new_dist = cur_dist + edge.dist;

                if (distance[edge.to] <= new_dist) continue;
                if (edge == banEdge) continue;

                pq.add(new int[] {edge.to, new_dist});
                distance[edge.to] = new_dist;
                if (banEdge == null) {
                    nodePath[edge.to] = cur_node;
                    edgePath[edge.to] = edge;
                }
            }
        }

        return distance[N];
    } 
}