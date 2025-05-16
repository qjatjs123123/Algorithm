import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static int[] posH = new int[2];
    static int[] posE = new int[2];
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        st = new StringTokenizer(br.readLine());
        posH[0] = Integer.parseInt(st.nextToken()) - 1;
        posH[1] = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        posE[0] = Integer.parseInt(st.nextToken()) - 1;
        posE[1] = Integer.parseInt(st.nextToken()) - 1;

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            
            for (int col = 0; col < M; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[N][M];
        
        int[][] dp = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) dp[row][col] = Integer.MAX_VALUE;
        }
        dp[posH[0]][posH[1]] = 0;
        
        Deque<int[]> deque = new ArrayDeque<>();
        Deque<int[]> deque1 = new ArrayDeque<>();
        deque.add(new int[] {posH[0], posH[1], 0});
        int answer = Integer.MAX_VALUE;
        
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            if (cur_arr[0] == posE[0] && cur_arr[1] == posE[1]) {
                answer = cur_arr[2];
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[0] + dy[i];
                int new_col = cur_arr[1] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (visited[new_row][new_col]) continue;
                if (graph[new_row][new_col] == 1) {
                    deque1.add(new int[] {new_row, new_col, cur_arr[2] + 1});
                    dp[new_row][new_col] = cur_arr[2] + 1;
                    visited[new_row][new_col] = true;
                } else {
                    deque.add(new int[] {new_row, new_col, cur_arr[2] + 1});
                    visited[new_row][new_col] = true;
                }
            }
        }

        while (!deque1.isEmpty()) {
            int[] cur_arr = deque1.pollFirst();
        

            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[0] + dy[i];
                int new_col = cur_arr[1] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (graph[new_row][new_col] == 1) continue;
                if (dp[new_row][new_col] <= cur_arr[2] + 1) continue;
                
                deque1.add(new int[] {new_row, new_col, cur_arr[2] + 1});
                dp[new_row][new_col] = cur_arr[2] + 1;
                
            }
        }
        answer = Math.min(answer, dp[posE[0]][posE[1]]);
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
        
    }
}