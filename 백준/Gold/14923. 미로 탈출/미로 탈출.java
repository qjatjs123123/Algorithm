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

        boolean[][][] visited = new boolean[N][M][2];        
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {posH[0], posH[1], 1, 0});
        visited[posH[0]][posH[1]][1] = true;
        int answer = Integer.MAX_VALUE;
        
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int isUse = cur_arr[2];
            
            if (cur_arr[0] == posE[0] && cur_arr[1] == posE[1]) {
                answer = cur_arr[3];
                break;
            }

            // System.out.println(cur_arr[0] + " " + cur_arr[1]);
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[0] + dy[i];
                int new_col = cur_arr[1] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (visited[new_row][new_col][isUse]) continue;
                if (isUse == 1) {
                    if (graph[new_row][new_col] == 1 && !visited[new_row][new_col][0]) {
                        deque.add(new int[] {new_row, new_col, 0, cur_arr[3] + 1});
                        visited[new_row][new_col][0] = true;
                        visited[new_row][new_col][1] = true;
                    }

                    if (graph[new_row][new_col] == 0) {
                        deque.add(new int[] {new_row, new_col, 1, cur_arr[3] + 1});
                        visited[new_row][new_col][1] = true;
                    }
                } else {
                    if (graph[new_row][new_col] == 1) continue;
                    deque.add(new int[] {new_row, new_col, 0, cur_arr[3] + 1});
                    visited[new_row][new_col][isUse] = true;
                }
            }
        }

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}