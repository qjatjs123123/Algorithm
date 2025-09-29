import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] times = new int[N + 1];
            int[] counts = new int[N + 1];
            int[] dp = new int[N + 1];
            ArrayList<Integer>[] graph = new ArrayList[N + 1];
            
            
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
                dp[i] = times[i];
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                counts[to]++;
            }

            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());

            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 1; i <= N; i++) {
                if (counts[i] == 0) {
                    deque.add(i);
                    dp[i] = times[i];
                }
            }

            while (!deque.isEmpty()) {
                int cur_node = deque.pollFirst();

                ArrayList<Integer> list = graph[cur_node];
                for (int new_node : list) {
                    counts[new_node]--;

                    dp[new_node] = Math.max(dp[new_node], times[new_node] + dp[cur_node]);
                    if (counts[new_node] == 0) deque.add(new_node);
                }
            }

            sb.append(dp[W]).append("\n");
        }

        System.out.println(sb.toString());
    }
}