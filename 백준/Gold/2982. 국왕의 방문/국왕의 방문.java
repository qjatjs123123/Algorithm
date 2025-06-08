import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int A, B, K, G;
    static int[] kingNode;
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    static int[] timeLine = new int[1_000_001];
    static ArrayList<Edge>[] graph;
    static int[] kingEnd;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        kingNode = new int[G];
        kingEnd = new int[G];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < G; i++) kingNode[i] = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        for (int i = 0; i <= 1_000_000; i++) timeLine[i] = -1;
        
        dfs(0, 0);

        int[] distance = new int[N + 1];
        for (int i = 0; i < N + 1; i++) distance[i] = 999_999_999;

        distance[A] = K;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] {K, A});

        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_time = cur_arr[0];
            int cur_node = cur_arr[1];
            int king_idx = timeLine[cur_time];
            
            if (cur_time > distance[cur_node]) continue;
            
            ArrayList<Edge> list = graph[cur_node];
            for (Edge edge : list) {
                int weight = edge.dist;

                if (king_idx >=0 && isSame(cur_node, edge.to, kingNode[king_idx], kingNode[king_idx + 1]))
                    weight += kingEnd[king_idx] - cur_time;
                
                if (distance[edge.to] > cur_time + weight) {
                    distance[edge.to] = cur_time + weight;
                    pq.add(new int[] {cur_time + weight, edge.to});
                }
            }
        }

        System.out.println(distance[B] - K);
    }

    static boolean isSame(int userS, int userE, int kingS, int kingE) {
        return (Math.min(userS, userE) == Math.min(kingS, kingE)) && 
            (Math.max(userS, userE) == Math.max(kingS, kingE));
    }
    
    static void dfs(int node, int startTime) {
        if (node == G - 1) {
            return;
        }
        int cur_node = kingNode[node];
        int new_node = kingNode[node + 1];
        
        ArrayList<Edge> list = graph[cur_node];
        
        for (Edge edge : list) {
            if (edge.to == new_node) {
                int endTime = startTime + edge.dist;
                for (int i = startTime; i < endTime; i++) timeLine[i] = node;
                kingEnd[node] = endTime;
                dfs(node + 1, endTime);
                break;
            }
        }

        
    }
}