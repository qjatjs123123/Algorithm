import java.util.*;
import java.io.*;

class Main {
    static int[][] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        // 초기화: graph와 dp 배열
        for (int row = 0; row <= n; row++) {
            for (int col = 0; col <= n; col++) {
                if (row == col) {
                    graph[row][col] = 0;
                    continue;
                }
                graph[row][col] = Integer.MAX_VALUE;
            }
        }

        // 간선 정보 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (graph[from][to] > dist) {
                graph[from][to] = dist;
                dp[from][to] = 0; // 직접 연결이므로, 중간 경로는 0
            }
        }

        // 플로이드-워셜 알고리즘 수행
        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    if (graph[start][mid] == Integer.MAX_VALUE || graph[mid][end] == Integer.MAX_VALUE) continue;

                    if (graph[start][end] > graph[start][mid] + graph[mid][end]) {
                        dp[start][end] = mid;
                        graph[start][end] = graph[start][mid] + graph[mid][end];
                    }
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();

        // 거리 출력
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (graph[row][col] == Integer.MAX_VALUE) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(graph[row][col]).append(" ");
                }
            }
            sb.append("\n");
        }

        // 경로 출력
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (row == col || graph[row][col] == Integer.MAX_VALUE) {
                    sb.append("0").append("\n");
                } else {
                    List<Integer> route = new ArrayList<>();
                    trace(row, col, route);
                    sb.append(route.size()).append(" ");
                    for (int x : route) {
                        sb.append(x).append(" ");
                    }
                    sb.append("\n");
                }
            }
        }

        System.out.print(sb.toString());
    }

    // 경로 복원 함수 (재귀적으로 경로를 추적)
    static void trace(int start, int end, List<Integer> route) {
        if (dp[start][end] == 0) {
            route.add(start);
            if (start != end) route.add(end);
            return;
        }
        int mid = dp[start][end];
        trace(start, mid, route);
        route.remove(route.size() - 1); // 중복된 중간 노드 제거
        trace(mid, end, route);
    }
}
