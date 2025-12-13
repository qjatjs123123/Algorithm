import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static boolean[] visited;
    static boolean flg;
    static ArrayList<Edge>[] graph;
    static int start_node, end_node;
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
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
    
    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();

        N = fs.nextInt();
        M = fs.nextInt();

        graph = new ArrayList[N + 1];
        for (int row = 0; row <= N; row++) graph[row] = new ArrayList<>();

        int max_weight = 0; int min_weight = Integer.MAX_VALUE;
        for (int row = 0; row < M; row++) {
            int A = fs.nextInt();
            int B = fs.nextInt(); 
            int C = fs.nextInt();

            graph[A].add(new Edge(B, C));
            graph[B].add(new Edge(A, C));

            max_weight = Math.max(max_weight, C);
            min_weight = Math.min(min_weight, C);
        }

        start_node = fs.nextInt();
        end_node = fs.nextInt();
        int start = min_weight;
        int end = max_weight;

        while (start <= end) {
            int mid = (start + end) / 2;
            flg = false;
            visited = new boolean[N + 1];
            visited[start_node] = true;

            dfs(start_node, mid);

            if (flg) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            
        }

        System.out.println(end);
    }

    static void dfs(int node, int target) {
        if (node == end_node) {
            flg = true;
            return;
        }

        ArrayList<Edge> list = graph[node];
        for (Edge edge: list) {
            if (edge.dist < target) continue;
            if (visited[edge.to]) continue;
            
            visited[edge.to] = true;
            dfs(edge.to, target);
        }
    }
}