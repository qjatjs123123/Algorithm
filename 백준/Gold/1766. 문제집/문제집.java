import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

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

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    public static void main(String[] args) throws IOException{
        int N, M;
        FastScanner fs = new FastScanner();
        
        N = fs.nextInt();
        M = fs.nextInt();

        ArrayList<Integer>[] list = new ArrayList[N + 1];
        int[] count = new int[N + 1];
        for (int i = 0; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();

            count[to]++;
            list[from].add(to);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) pq.add(i);
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int node = pq.poll();

            if (count[node] == 0) sb.append(node).append(" ");

            ArrayList<Integer> new_list = list[node];
            for (int new_node : new_list) {
                count[new_node]--;

                if (count[new_node] == 0) {
                    pq.add(new_node);
                }
            }
        }

        System.out.println(sb.toString());
    }
}