import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Edge>[] graph;
    static int[] parents;
    static class Edge{
        int no;
        int dist;

        Edge(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        parents = new int[N + 1];

        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, dist));
            // graph[to].add(new Edge(from, dist));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);


        
    }

    static void dijkstra(int start, int end) {
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.add(new long[] {0, start});
        distance[start] = 0;

        while (!pq.isEmpty()) {
            long[] cur_arr = pq.poll();

            long total = cur_arr[0];
            int no = (int) cur_arr[1];

            if (distance[no] < total) continue;

            ArrayList<Edge> list = graph[no];
            for (Edge edge: list) {
                if (edge.dist + total >= distance[edge.no]) continue;

                pq.add(new long[] {edge.dist + total, edge.no});
                parents[edge.no] = no;
                distance[edge.no] = edge.dist + total;
            }
        }

        System.out.println(distance[end]);
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();
        int tmp = end;
        int cnt = 0;
        while (true) {
            stack.push(tmp);
            cnt++;
            
            if (tmp == start) {
                break;
            }
            
            tmp = parents[tmp];
            
        }

        while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        System.out.println(cnt);
        System.out.println(sb.toString());
    }
}