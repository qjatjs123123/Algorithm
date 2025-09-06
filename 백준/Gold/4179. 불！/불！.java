import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] fire_dp, jihoon_dp;
    static char[][] graph;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fire_dp = new int[N][M];
        jihoon_dp = new int[N][M];
        graph = new char[N][M];

        Deque<int[]> deque = new ArrayDeque<>();
        Deque<int[]> deque1 = new ArrayDeque<>();
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < M; col++) {
                graph[row][col] = str.charAt(col);
                fire_dp[row][col] = -1;
                jihoon_dp[row][col] = -1;

                if (graph[row][col] == 'J') {
                    deque1.add(new int[] {row, col, 0});
                    jihoon_dp[ row ][ col ] = 0;
        
                }
                if (graph[row][col] == 'F') {
                    deque.add(new int[] {row, col, 0});
                    fire_dp[ row ][ col ] = 0;
                }
            }
        }

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_row = cur_arr[0], cur_col = cur_arr[1], cur_dist = cur_arr[2];

            for (int i = 0; i < 4; i++) {
                int new_row = cur_row + dy[i];
                int new_col = cur_col + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (fire_dp[new_row][new_col] != -1) continue;
                if (graph[new_row][new_col] == '#') continue;

                fire_dp[new_row][new_col] = cur_dist + 1;
                deque.add(new int[] {new_row, new_col, cur_dist + 1});
            }
        }

        
        while (!deque1.isEmpty()) {
            int[] cur_arr = deque1.pollFirst();
            int cur_row = cur_arr[0], cur_col = cur_arr[1], cur_dist = cur_arr[2];

            if (cur_row == 0 || cur_row == N - 1 || cur_col == 0 || cur_col == M - 1) {
                System.out.println(cur_dist + 1);
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_row + dy[i];
                int new_col = cur_col + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (jihoon_dp[new_row][new_col] != -1) continue;
                if (graph[new_row][new_col] == '#') continue;
                if (fire_dp[new_row][new_col] != -1 && fire_dp[new_row][new_col] <= cur_dist + 1) continue;

                jihoon_dp[new_row][new_col] = cur_dist + 1;
                deque1.add(new int[] {new_row, new_col, cur_dist + 1});
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}