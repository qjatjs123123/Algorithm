import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        int[] times = new int[N + 1];
        int[] counts = new int[N + 1];
        int[] answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            times[i] = time;

            while (true) {
                int node = Integer.parseInt(st.nextToken());

                if (node == -1) break;
                graph[node].add(i);
                counts[i]++;
            }
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (counts[i] == 0) {
                deque.add(i);
                answer[i] = times[i];
            }
        }

        while (!deque.isEmpty()) {
            int node = deque.pollFirst();

            ArrayList<Integer> list = graph[node];

            for (int new_node : list) {
                counts[new_node]--;
                answer[new_node] = Math.max(answer[new_node], answer[node]);

                if (counts[new_node] == 0) {
                    deque.add(new_node);
                    answer[new_node] += times[new_node];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb.toString());
    }
}