import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
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
    
    static class Edge{
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static ArrayList<Edge>[] graph;
    static int N, M;
    static int S, E;
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();
        M = fs.nextInt();
        S = fs.nextInt();
        E = fs.nextInt();
        int left = Integer.MAX_VALUE;
        int right = 0;
        
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            int dist = fs.nextInt();

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));

            left = Math.min(left, dist);
            right = Math.max(right, dist);
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            boolean result = bfs(mid);
            if (result) left = mid + 1;
            else right = mid - 1;
            
        }

        System.out.println(right);
    }

    static boolean bfs(int mid) {
        boolean[] visited = new boolean[N + 1];

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(S);
        visited[S] = true;
        
        while (!deque.isEmpty()) {
            int no = deque.pollFirst();

            if (no == E) return true;
            
            ArrayList<Edge> list = graph[no];
            for (Edge edge: list) {
                if (visited[edge.to]) continue;
                if (edge.dist < mid) continue;

                deque.add(edge.to);
                visited[edge.to] = true;
            }
            
        }

        return false;
    }
}