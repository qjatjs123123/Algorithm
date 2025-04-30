import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    static int[] parents;
    static class Edge implements Comparable<Edge> {
        int from, to, dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        Edge[] EdgeArr = new Edge[E + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            EdgeArr[i] = new Edge(from, to, dist);
            pq.add(EdgeArr[i]);
        }

        parents = new int[V + 1];
        for (int i = 0; i < V + 1; i++) parents[i] = i;

        long answer = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!union(edge.from, edge.to)) continue;

            answer += edge.dist;
        }

        System.out.println(answer);
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