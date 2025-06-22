import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static char[][] graph;
    static int[][][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];
        dp = new int[N][M][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < M; col++) {
                graph[row][col] = str.charAt(col);
                for (int i = 0; i < 2; i++) dp[row][col][i] = 999999;

                if (graph[row][col] == 'S') {
                    pq.add(new int[] {0, 0, row, col});
                    dp[row][col][0] = 0;
                    dp[row][col][1] = 0;
                }
            }
        }

        int[] dy = new int[] {1, -1, 0, 0};
        int[] dx = new int[] {0, 0, 1, -1};
        
        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();

            boolean flg = false;
            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[2] + dy[i];
                int new_col = cur_arr[3] + dx[i];

                if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M) continue;
                if (graph[new_row][new_col] == 'F') {
                    System.out.println(cur_arr[0] + " " + cur_arr[1]);
                    flg = true;
                    break;
                }
                int pass = 0;
                int near = 0;

                if (graph[new_row][new_col] == 'g') pass++;
                else {
                    for (int j = 0; j < 4; j++) {
                        int new_new_row = new_row + dy[j];
                        int new_new_col = new_col + dx[j];

                        if (new_new_row < 0 || new_new_row >= N || 
                            new_new_col < 0 || new_new_col >= M) continue;

                        if (graph[new_new_row][new_new_col] == 'g') {
                            near++;
                            break;
                        }
                    }
                }

                if (dp[new_row][new_col][0] < cur_arr[0] + pass) continue;
                if (dp[new_row][new_col][0] == cur_arr[0] + pass && 
                    dp[new_row][new_col][1] <= cur_arr[1] + near) continue;
                
                pq.add(new int[] {cur_arr[0] + pass, cur_arr[1] + near, new_row, new_col});
                dp[new_row][new_col][0] = cur_arr[0] + pass;
                dp[new_row][new_col][1] = cur_arr[1] + near;
            }

            if (flg) break;
        }
    }
}