import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, K;
    static boolean[] visited;
    static ArrayList<Edge>[] graph;
    static int[] parents;
    static class Edge implements Comparable<Edge> {
        int from, to, dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
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
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        parents = new int[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            visited[Integer.parseInt(st.nextToken())] = true; 
        }

        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(from, to, dist));
            pq.add(new Edge(from, to, dist));
        }

        for (int i = 0; i <= N; i++) parents[i] = i;

        int result = 0;


        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int rootA = find(edge.from);
            int rootB = find(edge.to);

            if (visited[rootA] && visited[rootB]) continue;
            if (union(rootA, rootB)) continue;

            result += edge.dist;
           
        }

        System.out.println(result);
    }

    static int find(int node) {
        if (node == parents[node]) return node;

        return parents[node] = find(parents[node]);
    }

    static boolean union(int rootA, int rootB) {
        if (rootA == rootB) return true;

        if (visited[rootA])
            parents[rootB] = parents[rootA];
        else
            parents[rootA] = parents[rootB];
        return false;
    }
}