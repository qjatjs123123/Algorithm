import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] parents;
    static class Edge implements Comparable<Edge> {
        int dist;
        int from, to;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.dist, edge.dist);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];

        PriorityQueue<Edge> min_pq = new PriorityQueue<>();
        PriorityQueue<Edge> max_pq = new PriorityQueue<>();

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            int rDist = dist == 1 ? 0 : 1;
            
            min_pq.add(new Edge(from, to, dist));
            max_pq.add(new Edge(from, to, rDist));
        }
        int max_num = N - mst(min_pq);
        int min_num = mst(max_pq);

        System.out.println((max_num * max_num) - (min_num * min_num));
    }

    static int mst(PriorityQueue<Edge> pq) {
        init();

        int cnt = 0;
        int total = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!union(edge.from, edge.to)) continue;
            total += edge.dist;
            
            if (++cnt == N) break;
            
        }

        // System.out.println(total);
        return total;
    }
    
    static void init() {
        for (int i = 0; i < N + 1; i++) parents[i] = i;
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