import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<Integer>[] graph;
    static boolean[][] visited;
    static int[] answer;
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        visited = new boolean[N][10]; // [day][prev] 떡
        answer = new int[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(0, 0); // day=0, prev=0 (떡 번호는 1~9 이므로 0은 없음 의미)

        if (!found) {
            System.out.println("-1");
        }
    }

    static void dfs(int day, int prev) {
        if (found) return;
        if (day == N) {
            // 성공!
            for (int x : answer) System.out.println(x);
            found = true;
            return;
        }

        if (visited[day][prev]) return;
        visited[day][prev] = true;

        for (int curr : graph[day]) {
            if (curr == prev) continue;
            answer[day] = curr;
            dfs(day + 1, curr);
        }
    }
}
