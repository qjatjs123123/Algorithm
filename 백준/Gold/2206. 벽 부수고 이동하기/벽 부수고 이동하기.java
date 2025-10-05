import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static boolean[][][] visited;
    
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
        FastScanner fs = new FastScanner();
        N = fs.nextInt();
        M = fs.nextInt();

        graph = new int[N][M];

        for (int row = 0; row < N; row++) {
            String str = fs.next();

            for (int col = 0; col < M; col++) graph[row][col] = str.charAt(col) - '0';
        }
        
        visited = new boolean[N][M][2];

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {0, 0, 0, 1});
        visited[0][0][0] = true;
        int[] dy = new int[] {1, -1, 0, 0};
        int[] dx = new int[] {0, 0, 1, -1};
        
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];
            int cur_idx = cur_arr[2];
            int cur_dist = cur_arr[3];

            
            if (cur_row == N - 1 && cur_col == M - 1) {
                System.out.println(cur_dist);
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_row + dy[i];
                int new_col = cur_col + dx[i];
                
                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;

                if (graph[new_row][new_col] == 1) {
                    if (cur_idx == 0) {
                        if (visited[new_row][new_col][1]) continue;
                        visited[new_row][new_col][1] = true;
                        deque.add(new int[] {new_row, new_col, 1, cur_dist + 1});
                    }
                } else {
                    if (visited[new_row][new_col][cur_idx]) continue;
                    visited[new_row][new_col][cur_idx] = true;
                    deque.add(new int[] {new_row, new_col, cur_idx, cur_dist + 1});
                }
             }
        }

        System.out.println(-1);
    }
}