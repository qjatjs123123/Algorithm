import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] waterArr;
    static int[][] graph;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
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

        N = Integer.parseInt(st.nextToken());
        waterArr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int water = Integer.parseInt(st.nextToken());
            waterArr[i] = water;
        }

        graph = new int[N + 1][N + 1];
        parents = new int[N + 1];
        
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 1; col <= N; col++) graph[row][col] = Integer.parseInt(st.nextToken());
        }

        for (int row = 0; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (row == 0) {
                    pq.add(new Edge(row, col, waterArr[col]));
                    continue;
                }
                if (row == col) continue;
                pq.add(new Edge(row, col, graph[row][col]));
            }
        }
            
        for (int i = 0; i <= N; i++) parents[i] = i;

        int answer = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            if (!union(edge.from, edge.to)) continue;
            // System.out.println(edge.to + " " + edge.to + " " + edge.dist);
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