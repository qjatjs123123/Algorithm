import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int K, M, P;
    static ArrayList<Integer>[] graph;
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        
        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        int T = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            K = fs.nextInt();
            M = fs.nextInt();
            P = fs.nextInt();

            int counts[] = new int[M + 1];
            int answer[] = new int[M + 1];
            graph = new ArrayList[M + 1];
            
            for (int i = 0; i <= M; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < P; i++) {
                int from = fs.nextInt();
                int to = fs.nextInt();

                graph[from].add(to);
                counts[to]++;
            }
            
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 1; i <= M; i++) {
                if (counts[i] == 0) {
                    deque.add(i);
                    answer[i] = 1;
                }
            }

            int[][] memo = new int[M + 1][2];
            while (!deque.isEmpty()) {
                int start = deque.pollFirst();

                ArrayList<Integer> list = graph[start];
            
                for (int end : list) {
                    counts[end]--;
                    int max_num = memo[end][0];
                    
                    if (max_num < answer[start]) {
                        memo[end][0] = answer[start];
                        memo[end][1] = 1;
                    } else if (max_num == answer[start]) {
                        memo[end][1]++;
                    } else {}

                    
                    if (counts[end] == 0) {
                        if (memo[end][1] >= 2) answer[end] = memo[end][0] + 1;
                        else answer[end] = memo[end][0];

                        deque.add(end);
                    }
                }
            }
            sb.append(K).append(" ").append(answer[M]).append("\n");
        }

        System.out.println(sb.toString());
    }
}