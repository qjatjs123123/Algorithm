import java.util.*;
import java.io.*;

class Main {
    static int T;
    static int[] parents;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            parents = new int[N + 1];
            sb.append("Scenario ").append(t).append(":").append("\n");

            for (int i = 0; i <= N; i++) {
                parents[i] = i; // 초기화
            }

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if (find(u) == find(v)) { // 루트를 비교해야 함
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    static int find(int node) {
        if (parents[node] == node) {
            return node;
        }
        return parents[node] = find(parents[node]); // 경로 압축
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parents[rootB] = rootA; // 올바른 병합
        }
    }
}
