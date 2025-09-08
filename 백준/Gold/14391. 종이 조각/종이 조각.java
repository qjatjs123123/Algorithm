import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dy = new int[] {1, 0};
    static int[] dx = new int[] {0, 1};
    static int answer = 0;
    static int tmp = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        graph = new int[N][M]; visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            for (int col = 0; col < M; col++) graph[row][col] = str.charAt(col) - '0';
        }

        backtracking(0, 0);

        System.out.println(answer);
    }

    static void backtracking(int row, int col) {
        int next_row = row, next_col = col;

        if (col == M - 1) {
            next_col = 0;
            next_row++;
        } else {
            next_col++;
        }

        if (row == N) {
            answer = Math.max(answer, tmp);
            return;
        }

        if (visited[row][col]) {
            backtracking(next_row, next_col);
            return;
        }
        
        for (int i = 0; i < 2; i++) {
            int base_row = row;
            int base_col = col;

            StringBuilder sb = new StringBuilder();
            Stack<int[]> stack = new Stack<>();
            
            while (true) {
                stack.push(new int[] {base_row, base_col});
                
                for (int[] arr : stack)
                    visited[ arr[0] ][ arr[1] ] = true;      
                sb.append(graph[base_row][base_col]);
                tmp += Integer.parseInt(sb.toString());

                backtracking(next_row, next_col);

                for (int[] arr : stack)
                    visited[ arr[0] ][ arr[1] ] = false;
                tmp -= Integer.parseInt(sb.toString());
    
                base_row += dy[i]; base_col += dx[i];
                if ( base_row < 0 || base_row == N 
                     || base_col < 0 || base_col == M
                       || visited[base_row][base_col]) break; 
            }
        }
    }
}