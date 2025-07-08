import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static int answer = 0;
    static boolean[][] visited;
    static int[][][] direction = {
        { {0, -1}, {1, 0}, {0, 0} },
        { {0, -1}, {-1, 0}, {0, 0} },
        { {0, 1}, {-1, 0}, {0, 0} },
        { {0, 1}, {1, 0}, {0, 0} }
    };
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) graph[row][col] = Integer.parseInt(st.nextToken());
        }
        
        backtracking(0, 0, 0);
        System.out.println(answer);
    }

    static void backtracking(int center_row, int center_col, int total) {
        if (center_row == N ) {
            answer = Math.max(answer, total);
            return;
        }

        
        int new_row = center_row;
        int new_col = center_col;

        if (new_col == M - 1) {
            new_col = 0;
            new_row++;
        }else {
            new_col++;
        }

        backtracking(new_row, new_col, total);

        for (int[][] direct : direction) {
            boolean isValid = true;
            int tmp = 0;
            int idx = 0;
            
            for (int[] pos : direct) {
                int tmp_row = center_row + pos[0];
                int tmp_col = center_col + pos[1];

                if (tmp_row < 0 || tmp_row == N || tmp_col < 0 || tmp_col == M
                   || visited[tmp_row][tmp_col]) {
                    isValid = false;
                    break;
                }

                tmp += graph[tmp_row][tmp_col];
                if (idx == 2) tmp += graph[tmp_row][tmp_col];
                idx++;
            }

            if (!isValid) continue;
            
            for (int[] pos : direct) {
                int tmp_row = center_row + pos[0];
                int tmp_col = center_col + pos[1];

                visited[tmp_row][tmp_col] = true;
            }

            backtracking(new_row, new_col, total + tmp);

            for (int[] pos : direct) {
                int tmp_row = center_row + pos[0];
                int tmp_col = center_col + pos[1];

                visited[tmp_row][tmp_col] = false;
            }
        }
        
    }
}