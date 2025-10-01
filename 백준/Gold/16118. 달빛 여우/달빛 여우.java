import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Edge>[] graph;
    
    static class Info implements Comparable<Info> {
        long dist;
        int no;
        boolean isFast;

        Info(long dist, int no, boolean isFast) {
            this.dist = dist;
            this.no = no;
            this.isFast = isFast;
        }

        public int compareTo(Info o) {
            return Long.compare(this.dist, o.dist);
        }
    }
    
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException{
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        N = fs.nextInt();
        M = fs.nextInt();
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            int dist = fs.nextInt() * 2;

            graph[from].add(new Edge(to, dist));
            graph[to].add(new Edge(from, dist));
        }

        long[][] foxDistance = dijkstra(false);
        long[][] wolfDistance = dijkstra(true);

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            long wolf = Math.min(wolfDistance[0][i], wolfDistance[1][i]);
            long fox = foxDistance[0][i];

            if (wolf > fox) answer++;
        }


        System.out.println(answer);
    }

    static long[][] dijkstra(boolean isWolf) {
        long[][] distance = new long[2][N + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N + 1; j++) distance[i][j] = Long.MAX_VALUE;
        }
        if (!isWolf) {
            distance[0][1] = 0;
        } else {
            distance[0][1] = 0;
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 1, false));

        // true 1 false 0
        while(!pq.isEmpty()) {
            Info info = pq.poll();

            int cur_isFast = info.isFast ? 1 : 0;
            if (distance[cur_isFast][info.no] < info.dist) continue;

            ArrayList<Edge> list = graph[info.no];
            for (Edge edge: list) {
                long new_dist = 0;
                boolean f = true;
                if (!isWolf) {
                    new_dist = info.dist + edge.dist;
                    f = false;
                } else {
                    if (!info.isFast)
                        new_dist = info.dist + ( edge.dist / 2 );
                    else 
                        new_dist = info.dist + ( edge.dist * 2 );
                    
                    f = !info.isFast;
                }

                int new_isFast = cur_isFast;
                if (isWolf) {
                    new_isFast = (cur_isFast + 1) % 2;
                }
                
                if (distance[new_isFast][edge.to] <= new_dist) continue;

                distance[new_isFast][edge.to] = new_dist;
                
                pq.add(new Info(new_dist, edge.to, f));
            }
        }
        
        return distance;
    }
}