import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, K;
    static int[] parents;
    static PriorityQueue<Edge> origin_pq;
    static ArrayList<Edge>[] graph;
    static int idx = 0;
    
    static class Edge implements Comparable<Edge> {
        int from, to;
        int dist;

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
        K = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        origin_pq = new PriorityQueue<>();
        graph = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int dist = 1; dist <= M; dist++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(from, to, dist));
            graph[to].add(new Edge(to, from, dist));
            origin_pq.add(new Edge(from, to, dist));
        }
        boolean flg = false;
        StringBuilder sb = new StringBuilder();
        
        for (int k = 0; k < K; k++) {
            PriorityQueue<Edge> pq = deepCopy();
            int j = 0;
            while (!pq.isEmpty()) {
                if (j == idx) break;
                pq.poll();
                j++;
            }
            for (int i = 0; i <= N; i++) parents[i] = i;
            int count = 0;
            int dist = 0;
            boolean tmp = false;
                

            if (flg) {
                sb.append(0).append(" ");
                continue;
            }
            
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                
                if (!union(edge.from, edge.to)) continue;

                dist += edge.dist;
                if (++count == N - 1) {
                    tmp = true;
                    idx++;
                    break;
                }
            }

            if (tmp) sb.append(dist).append(" ");
            else {
                sb.append(0).append(" ");
                flg = true;
            }
        }

        System.out.println(sb.toString());
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
    static PriorityQueue<Edge> deepCopy() {
        PriorityQueue<Edge> tmp = new PriorityQueue<>();

        for (Edge edge : origin_pq) {

            tmp.add(new Edge(edge.from, edge.to, edge.dist));

        }

        return tmp;
    }
}