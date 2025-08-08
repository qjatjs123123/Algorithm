import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] parents;
    static Pos[] posArr;
    
    static class Pos {
        int x, y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }   

    static class Edge implements Comparable<Edge>{
        int from, to;
        double dist;

        Edge(int from, int to) {
            this.from = from;
            this.to = to;

            dist = Math.sqrt( Math.pow( posArr[from].x - posArr[to].x, 2 ) + 
                            Math.pow( posArr[from].y - posArr[to].y, 2 ));
        }

        public int compareTo(Edge edge) {
            if (this.dist > edge.dist) return 1;
            else if (this.dist == edge.dist) return 0;
            else return -1;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) parents[i] = i;

        posArr = new Pos[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            posArr[i] = new Pos(x, y);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                pq.add(new Edge(i, j));
            }
        }

        int K = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (union(from, to)) {
                K++;
            }
        }

        double answer = 0;
        while (!pq.isEmpty()) {
            if (K == N - 1) break;
            
            Edge edge = pq.poll();
            
            if (union(edge.from, edge.to)) {
                answer += edge.dist;
                K++;
            }
        }

        System.out.printf("%.2f",answer);
    }

    static int find(int node) {
        if (parents[node] == node) return node;

        return parents[node] = find(parents[node]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootA] = parents[rootB];
        return true;
    }
}