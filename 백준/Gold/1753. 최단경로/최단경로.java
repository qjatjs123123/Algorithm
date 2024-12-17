import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int V, E;
    static int K;
    static ArrayList<ArrayList<Pos>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for (int row = 0; row <= V; row++) {
            ArrayList<Pos> new_list = new ArrayList<>();
            graph.add(new_list);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            ArrayList<Pos> cur_list = graph.get(from);
            cur_list.add(new Pos(to, weight));

            
        }
        
        PriorityQueue<Pos> pq = new PriorityQueue<>();

        int[] distance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        for (int i = 0; i <= V; i++) distance[i] = Integer.MAX_VALUE;
        distance[K] = 0;
        pq.add(new Pos(K, 0));

        while(!pq.isEmpty()) {
            Pos cur_pos = pq.poll();    

            int cur_node = cur_pos.v;
            int cur_weight = cur_pos.w;
            ArrayList<Pos> new_graph = graph.get(cur_node);

            if (new_graph.size() == 0) continue;
            if (cur_weight > distance[cur_node]) continue;

            for (Pos new_pos : new_graph) {
                int new_node = new_pos.v;
                int new_weight = new_pos.w;

                if (distance[new_node] > cur_weight + new_weight) {
                    distance[new_node] = cur_weight + new_weight;
                    pq.add(new Pos(new_node, cur_weight + new_weight));
                }
            }
            
        }

        StringBuilder sb = new StringBuilder();

        for (int col = 1; col <= V; col++) {
            if (distance[col] == Integer.MAX_VALUE) sb.append("INF");
            else sb.append(distance[col]);

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static class Pos implements Comparable<Pos> {
        int v, w;

        Pos(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Pos pos) {
            return Integer.compare(this.w, pos.w);
        }
    }
}