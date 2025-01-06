import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static int start_node, end_node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new int[]{B, C});
            graph.get(B).add(new int[]{A, C});
        }

        st = new StringTokenizer(br.readLine());
        start_node = Integer.parseInt(st.nextToken());
        end_node = Integer.parseInt(st.nextToken());

        int start = 1;
        int end = 1_000_000_000;
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (bfs(mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean bfs(int weight) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(start_node);
        visited[start_node] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == end_node) return true;

            for (int[] edge : graph.get(cur)) {
                int next = edge[0];
                int edgeWeight = edge[1];

                if (!visited[next] && edgeWeight >= weight) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        return false;
    }
}
