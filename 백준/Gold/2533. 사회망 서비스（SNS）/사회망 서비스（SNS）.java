import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 그래프 초기화
        memo = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            Arrays.fill(memo[i], -1);
        }

        // 그래프 입력 받기
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // 결과 계산
        int ans = Math.min(dp(1, 1, -1), dp(1, 0, -1));
        System.out.println(ans);
    }

    static int dp(int node, int isAdaptor, int parent) {
        if (memo[node][isAdaptor] != -1) return memo[node][isAdaptor];

        int total = 0;
        for (int next : graph.get(node)) {
            if (next != parent) { // 부모 노드는 다시 방문하지 않음
                if (isAdaptor == 1) {
                    total += Math.min(dp(next, 1, node), dp(next, 0, node));
                } else {
                    total += dp(next, 1, node);
                }
            }
        }

        memo[node][isAdaptor] = total + isAdaptor; // 현재 노드가 어댑터면 +1
        return memo[node][isAdaptor];
    }
}
