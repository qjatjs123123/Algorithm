import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[][] posArr;
    static int[] parents;
    
    static class Edge implements Comparable<Edge> {
        int from, to;
        double dist;

        Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        } 
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        posArr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            posArr[i][0] = X;
            posArr[i][1] = Y;
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) parents[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            union(from, to);
        }

        double answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int[] fromArr = posArr[i];
                int[] toArr = posArr[j];

                double dist = Math.sqrt( Math.pow(fromArr[0] - toArr[0] , 2) 
                                        + Math.pow(fromArr[1] - toArr[1], 2) );

                pq.add(new Edge(i, j, dist));
            }
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            if (!union(edge.from, edge.to)) continue;

            
            answer += edge.dist;
        }

        System.out.printf("%.2f", answer);
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