import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] parents;
    static int N, M;
    static ArrayList<Edge>[] graph;
    static char[] typeArr;
    
    static class Edge implements Comparable<Edge> {
        int from, to, dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        char nextChar() throws IOException {
            return next().charAt(0);
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();
        M = fs.nextInt();

        graph = new ArrayList[N + 1];
        typeArr = new char[N + 1];
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            char c = fs.nextChar();

            graph[i] = new ArrayList<>();
            typeArr[i] = c;
            parents[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < M; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            int dist = fs.nextInt();

            if (typeArr[from] == typeArr[to]) continue;
            graph[from].add(new Edge(from, to, dist));
            graph[to].add(new Edge(to, dist, from));
            pq.add(new Edge(from, to, dist));
        }

        int cnt = 0;
        int answer = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            if (cnt == N - 1) break;
            if (!union(edge.from, edge.to)) continue;

            cnt++;
            answer += edge.dist;
            
        }

        if (cnt == N - 1)
            System.out.println(answer);
        else 
            System.out.println(-1);
        
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootB] = parents[rootA];
        return true;
    }
}