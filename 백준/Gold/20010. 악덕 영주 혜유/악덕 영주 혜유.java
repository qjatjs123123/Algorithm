import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static ArrayList<Edge>[] graph;
    static int[] parents;
    static ArrayList<Edge>[] dfs_graph;
    static boolean[] visited;
    static long MAX = 0;
    static int start = 0;
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        long dist;
        
        Edge(int from, int to, long dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo(Edge other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt(); K = fs.nextInt();

        graph = new ArrayList[N];
        dfs_graph = new ArrayList[N];
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
            graph[i] = new ArrayList<>();
            dfs_graph[i] = new ArrayList<>();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            long dist = (long) fs.nextInt();

            Edge edge1 = new Edge(from, to, dist);
            Edge edge2 = new Edge(to, from, dist);
            
            graph[from].add(edge1);
            graph[to].add(edge2);
            pq.add(edge1);
            pq.add(edge2);
        }

        long answer = 0;
        int cnt = 0;

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (union(edge.from, edge.to) == false) continue;     
            answer += edge.dist; 
            cnt++;
            dfs_graph[edge.to].add(new Edge(edge.to, edge.from, edge.dist));
            dfs_graph[edge.from].add(new Edge(edge.from, edge.to, edge.dist));
            if (cnt == N - 1) break;
        }

        visited = new boolean[N];
        visited[0] = true;
        dfs(0, 0);

        MAX = 0;
        visited = new boolean[N];
        visited[start] = true;

        dfs(start, 0);
        System.out.println(answer);
        System.out.println(MAX);
    }

    
    static void dfs(int node, long dist) {

        boolean result = false;
        ArrayList<Edge> list = dfs_graph[node];

        for (Edge edge: list) {
            if (visited[edge.to]) continue;

            visited[edge.to] = true;
            result = true;
            dfs(edge.to, dist + edge.dist);
        }

        if (result == false) {
            if (MAX < dist) {
                MAX = dist;
                start = node;
            }
        }
    }
    
    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootA] = parents[rootB];
        return true;
    }
}