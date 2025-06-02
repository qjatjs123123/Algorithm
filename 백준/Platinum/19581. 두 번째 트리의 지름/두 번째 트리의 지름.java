import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static boolean[] visited;
    static int N;
    static ArrayList<Edge>[] graph;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    static int max_node = 0;
    static int max_value = 0;
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    static int answer = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        visited[1] = true;
        dfs(1, 0 , false);
        visited[1] = false;
        int first_node = max_node;
        max_node = 0;
        max_value = 0;
        
        visited[first_node] = true;
        dfs(first_node, 0, false);
        visited[first_node] = false;

        int second_node = max_node;
        // System.out.println(max_node + " " + first_node);
        max_node = 0;
        max_value = 0;

        visited[second_node] = true;
        dfs(second_node, 0, true);
        visited[second_node] = false;
        pq.poll();
        answer = Math.max(answer, pq.poll());

        // System.out.println(answer);
        
        pq.clear();
        max_node = 0;
        max_value = 0;
        visited[first_node] = true;
        dfs(first_node, 0, true);
        pq.poll();
        answer = Math.max(answer, pq.poll());

        System.out.println(answer);
    }

    static void dfs(int node, int value, boolean isSave) {
        ArrayList<Edge> list = graph[node];
        boolean isflg = false;

        for (Edge edge : list) {
            if (visited[edge.to]) continue;

            visited[edge.to] = true;
            dfs(edge.to, value + edge.dist, isSave);
            visited[edge.to] = false;
            isflg = true;
        }
        if (isSave) pq.add(value);
        if (!isflg) {
            if (max_value < value) {
                max_value = value;
                max_node = node;
            }

            return;
        }
    }
}