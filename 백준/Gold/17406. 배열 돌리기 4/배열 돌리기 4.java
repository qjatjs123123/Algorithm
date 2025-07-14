import java.util.*;
import java.io.*;

class Main {
    static int N, M, K;
    static int[][] graph, origin;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] visited;
    static Stack<Integer> stack = new Stack<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][M + 1];
        origin = new int[N + 1][M + 1];
        visited = new int[K];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= M; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
                origin[row][col] = graph[row][col];
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            list.add(new int[]{row, col, s});
            visited[i] = 0;
        }

        backtracking(0);
        System.out.println(answer);
    }

    static void deepCopy() {
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= M; col++) {
                graph[row][col] = origin[row][col];
            }
        }
    }

    static void findMinNum() {
        for (int row = 1; row <= N; row++) {
            int sum = 0;
            for (int col = 1; col <= M; col++) {
                sum += graph[row][col];
            }
            answer = Math.min(answer, sum);
        }
    }

    static void backtracking(int depth) {
        if (depth == K) {
            deepCopy(); // ✅ 이 위치에서 원본 복사
            for (int idx : stack) {
                rotate(list.get(idx));
            }
            findMinNum();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i] == 1) continue;
            visited[i] = 1;
            stack.push(i);
            backtracking(depth + 1);
            stack.pop();
            visited[i] = 0;
        }
    }

    static void rotate(int[] op) {
        int r = op[0];
        int c = op[1];
        int s = op[2];

        for (int layer = 1; layer <= s; layer++) {
            int top = r - layer;
            int bottom = r + layer;
            int left = c - layer;
            int right = c + layer;

            int tmp = graph[top][left];

            // 위쪽
            for (int i = top; i < bottom; i++) graph[i][left] = graph[i + 1][left];
            // 왼쪽
            for (int i = left; i < right; i++) graph[bottom][i] = graph[bottom][i + 1];
            // 아래쪽
            for (int i = bottom; i > top; i--) graph[i][right] = graph[i - 1][right];
            // 오른쪽
            for (int i = right; i > left + 1; i--) graph[top][i] = graph[top][i - 1];

            graph[top][left + 1] = tmp;
        }
    }
}
