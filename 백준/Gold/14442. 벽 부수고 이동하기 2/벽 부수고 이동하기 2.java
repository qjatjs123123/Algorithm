import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, K;
    static int[][] graph;
    static boolean[][][] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int col = 0; col < M; col++) {
                graph[row][col] = str.charAt(col) - '0';
            }
        }
        
        visited = new boolean[N][M][K + 1];

        Deque<int[]> deque = new ArrayDeque<int[]>();
        deque.add(new int[] {0, 0, 0, 0});
        int[] dy = new int[] {0, 0, 1, -1};
        int[] dx = new int[] {1, -1, 0, 0};
        
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];
            int cur_cnt = cur_arr[2];

            if (cur_row == N - 1 && cur_col == M -1) {
                System.out.println(cur_arr[3] + 1);
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_row + dy[i];
                int new_col = cur_col + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (visited[new_row][new_col][cur_cnt]) continue;

                if (graph[new_row][new_col] == 1) {
                    if (cur_cnt < K && !visited[new_row][new_col][cur_cnt + 1]) {
                        deque.add(new int[] {new_row, new_col, cur_cnt + 1, cur_arr[3] + 1});
                        visited[new_row][new_col][cur_cnt + 1] = true;
                        // visited[new_row][new_col][cur_cnt] = true;
                    }
                }else {
                    deque.add(new int[] {new_row, new_col, cur_cnt, cur_arr[3] + 1});
                    visited[new_row][new_col][cur_cnt] = true;
                }
            }
        }

        System.out.println(-1);
    }
}