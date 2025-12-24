import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

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

        int tc = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tc; i++) {
            int n = fs.nextInt();
            boolean[][] graph = new boolean[n + 1][n + 1];

            int[] scoreArr = new int[n + 1];
            int[] arr = new int[n + 1];
            
            for (int j = 1; j <= n; j++) {
                int num = fs.nextInt();
                scoreArr[ num ] = j;
                arr[j] = num;
            }

            for (int from = 1; from <= n; from++) {
                for (int to = from + 1; to <= n; to++) {
                    graph[ arr[from] ][ arr[to] ] = true;
                }
            }
            
            int[] counts = new int[n + 1];
            for (int j = 1; j <= n; j++) counts[ arr[j] ] = j - 1; 

            // System.out.println(Arrays.toString(arr));
            // System.out.println(Arrays.toString(counts));
            // System.out.println(Arrays.toString(scoreArr));
            
            int m = fs.nextInt();
            for (int j = 0; j < m; j++) {
                int a = fs.nextInt();
                int b = fs.nextInt();

                if (scoreArr[a] > scoreArr[b]) {
                    // a가 b보다 먼저 올 때,
                    counts[a]--;
                    counts[b]++;

                    // graph[b][a] = true;
                    // graph[a][b] = false;
                    
                    graph[b][a] = false;
                    graph[a][b] = true;
                } else {
                    counts[a]++;
                    counts[b]--;

                    // graph[b][a] = false;
                    // graph[a][b] = true;
                    
                    graph[b][a] = true;
                    graph[a][b] = false;
                }

            }


            StringBuilder sbb = new StringBuilder();
            Deque<Integer> deque = new ArrayDeque<>();
            Deque<Integer> answer = new ArrayDeque<>();
            
            for (int j = 1; j <= n; j++) {
                if (counts[j] == 0) {
                    deque.add(j);
                    answer.add(j);
                }
            }

            while (!deque.isEmpty()) {
                int no = deque.pollFirst();

                for (int j = 1; j <= n; j++) {
                    if (graph[no][j]) {
                        counts[j]--;

                        if (counts[j] == 0) {
                            deque.add(j);
                            answer.add(j);
                        }
                    }
                }
            }

        
            if (answer.size() != n) sb.append("IMPOSSIBLE");
            else {
                for (int no : answer) {
                    sbb.append(no).append(" ");
                }
                sb.append(sbb.toString());
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}