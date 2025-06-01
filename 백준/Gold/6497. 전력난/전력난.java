import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int m, n;
    static ArrayList<Edge>[] graph;
    static int[] parents;
    static class Edge implements Comparable<Edge> {
        int from, to, dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge edge){
            return Integer.compare(this.dist, edge.dist);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        if (m == 0 && n == 0) break;
        graph = new ArrayList[m + 1];

        for (int i = 0; i <= m; i++) graph[i] = new ArrayList<>();

        long total = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            total += dist;
            graph[from].add(new Edge(from, to, dist));
            graph[to].add(new Edge(to, from, dist));
            pq.add(new Edge(from, to, dist));
        }

        parents = new int[m + 1];
        for (int i = 0; i <= m; i++) parents[i] = i;

        long result = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!union(edge.from, edge.to)) continue;
            result += edge.dist;
            if (++count == m - 1) break;
        }
        
        System.out.println(total - result);
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;

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