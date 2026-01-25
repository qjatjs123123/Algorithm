import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, A, B, C;
    static ArrayList<Edge>[] graph;
    static int[] distance;
    static int[] dp;
    static class Edge {
        int from, to, dist;

        Edge(int from, int to, int dist) {
            this.from = from;
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
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        N = fs.nextInt(); M = fs.nextInt(); A = fs.nextInt(); B = fs.nextInt(); C = fs.nextInt();
        graph = new ArrayList[N + 1];
        distance = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            distance[i] = Integer.MAX_VALUE;
        }
        
        
        for (int i = 0; i < M; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            int dist = fs.nextInt();

            graph[from].add(new Edge(from, to, dist));
            graph[to].add(new Edge(to, from, dist));
        }

        int result = dijkstra();

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
        
    }

    static int dijkstra() {
        distance[A] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] {0, A, 0});
        int answer = Integer.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_dist = cur_arr[0];
            int cur_no = cur_arr[1];
            int cur_max = cur_arr[2];
            
            if (distance[cur_no] < cur_dist) continue;
            
            ArrayList<Edge> list = graph[cur_no];
            for (Edge edge : list) {
                int new_dist = cur_dist + edge.dist;
                int new_max = Math.max(cur_max, edge.dist);
                
                if (distance[edge.to] <= new_dist) continue;
                if (edge.to == B && new_dist <= C) {
                    answer = Math.min(answer, new_max);
                }
                
                distance[edge.to] = new_dist;
                pq.add(new int[] {new_dist, edge.to, new_max});
            }
        }

        return answer;
    }
}