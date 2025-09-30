import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException{
            return Integer.parseInt(next());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();
        M = fs.nextInt();

        ArrayList<int[]>[] graph = new ArrayList[N + 1];
        boolean[] visited = new boolean[N + 1];
        ArrayList[] counts = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
            counts[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int k = fs.nextInt();
            Stack<Integer> stack = new Stack<>();

            for (int j = 0; j < k; j++) {
                stack.push(fs.nextInt());
            }

            int r = fs.nextInt();
            int idx = counts[r].size();
            counts[r].add(stack.size());

            while(!stack.isEmpty()) graph[stack.pop()].add(new int[] {r, idx});
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int L = fs.nextInt();

        for (int j = 0; j < L; j++) {
            int y = fs.nextInt();

            counts[y].clear();
            deque.add(y);
            visited[y] = true;
        }

        while (!deque.isEmpty()) {
            int node = deque.pollFirst();

            ArrayList<int[]> list = graph[node];

            for (int[] new_arr : list) {
                int new_node = new_arr[0];
                int new_idx = new_arr[1];

                if (counts[new_node].isEmpty()) continue;
            
                if ((int)counts[new_node].get(new_idx) <= 0) continue;

                int tmp = (int)counts[new_node].get(new_idx) - 1;
                counts[new_node].set(new_idx, tmp);
                if ((int)counts[new_node].get(new_idx) == 0) {
                    visited[new_node] = true;
                    deque.add(new_node);
                    counts[new_node].clear();
                }
            }
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                count++;
                sb.append(i).append(" ");
            }
        }

        System.out.println(count);
        System.out.println(sb.toString());
    }
}