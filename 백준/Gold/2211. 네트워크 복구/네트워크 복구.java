import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Edge>[] graph;
    static int[] visited;
    static class Edge{
        int no;
        int dist;

        Edge(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }

    static class Info implements Comparable<Info>{
        int dist, from;

        Info (int dist, int from) {
            this.dist = dist;
            this.from = from;
        }

        public int compareTo(Info info) {
            return this.dist - info.dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new int[N + 1];

        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int row = 1; row <= N; row++) {
            if (visited[row] != 0) {
                cnt++;
                sb.append(row).append(" ").append(visited[row]).append("\n");
            }
        }

        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    static void dijkstra() {
        int[] distance = new int[N + 1];
        for (int i = 0; i < N + 1; i++) distance[i] = Integer.MAX_VALUE;
        ArrayList<Integer>[] trackList = new ArrayList[N + 1];

        PriorityQueue<Info> pq = new PriorityQueue<>();
        Info tmp = new Info(0, 1);
        distance[1] = 0;

        pq.add(tmp);

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (distance[info.from] < info.dist) continue;
            ArrayList<Edge> list = graph[info.from];

            for (Edge edge : list) {
                if (info.dist + edge.dist >= distance[edge.no]) continue;
                
                Info t = new Info(info.dist + edge.dist, edge.no);
                visited[edge.no] = info.from;
                distance[edge.no] = info.dist + edge.dist;
                pq.add(t);
            }
            
        }

    }

    static ArrayList<Integer> deepCopy(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer n : list) result.add(n);

        return result;
    }
}