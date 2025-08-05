import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M, A, B;
    static long C;
    static long min_num = Long.MAX_VALUE;
    static long max_num = 0;
    static ArrayList<Edge>[] graph;

    static class Edge {
        int no;
        long dist;

        Edge(int no, long dist) {
            this.no = no;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken()); // long 파싱

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            long V = Long.parseLong(st.nextToken());

            graph[S].add(new Edge(E, V));
            graph[E].add(new Edge(S, V));

            min_num = Math.min(min_num, V);
            max_num = Math.max(max_num, V);
        }

        long start = min_num;
        long end = max_num;

        while (start <= end) {
            long mid = (start + end) / 2;

            boolean result = bfs(mid);

            if (!result) start = mid + 1;
            else end = mid - 1;
        }

        if (start > max_num) System.out.println(-1);
        else System.out.println(start);
    }

    static boolean bfs(long mid) {
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.add(new long[] {0, A});
        visited[A] = true;

        long min = Long.MAX_VALUE;
        
        while (!pq.isEmpty()) {
            long[] cur_arr = pq.poll();

            if (cur_arr[1] == B) {
                min = Math.min(min, cur_arr[0]);
                continue;
            }

            for (Edge edge: graph[(int)cur_arr[1]]) {
                if (edge.dist > mid || visited[edge.no]) continue;
                if (edge.no != B)
                    visited[edge.no] = true;
                pq.add(new long[] {edge.dist + cur_arr[0], edge.no});
            }
        }

        return min <= C;
    }
}
