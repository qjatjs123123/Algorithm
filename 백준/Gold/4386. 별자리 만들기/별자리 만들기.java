import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] parents;
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

        double nextDouble() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Double.parseDouble(st.nextToken());
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        double dist;

        Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        } 

        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();
        
        parents = new int[N + 1];
        double[][] list = new double[N + 1][2];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;

            list[i][0] = fs.nextDouble();
            list[i][1] = fs.nextDouble();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int from = 1; from <= N; from++) {
            for (int to = from + 1; to <= N; to++) {
                double dist = getDistance(list[from], list[to]);
                pq.add(new Edge(from, to, dist));
            }
        }

        int cnt = 0;
        double answer = 0;

        while (true) {
            Edge edge = pq.poll();
            if (union(edge.from, edge.to) == false) continue;

            cnt++;
            answer += edge.dist;

            if (cnt == N - 1) break;
        }

        System.out.printf("%.2f", answer);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootA] = parents[rootB];
        return true;
    }
    
    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    
    static double getDistance(double[] fromArr, double[] toArr) {
        return Math.sqrt( Math.pow(fromArr[0] - toArr[0], 2) + Math.pow(fromArr[1] - toArr[1], 2) );
    }
}