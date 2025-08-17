import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int R, C;
    static char[][] graph;
    static boolean[][] water_visited, biber_visited;
    static Deque<int[]> water_deque, biber_deque;
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        water_visited = new boolean[R][C];
        biber_visited = new boolean[R][C];
        water_deque = new ArrayDeque<>();
        biber_deque = new ArrayDeque<>();

        for (int row = 0; row < R; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < C; col++) {
                graph[row][col] = str.charAt(col);

                if (graph[row][col] == '*') {
                    water_deque.add(new int[] {row, col});
                    water_visited[row][col] = true;
                }

                if (graph[row][col] == 'S') {
                    biber_deque.add(new int[] {row, col, 0});
                    biber_visited[row][col] = true;
                }
            }
        }

        int time = 0;
        while (true) {
            if (biber_deque.size() == 0) break;

            spread_water();
            boolean result = spread_biber();

            if (result) {
                System.out.println(time + 1);
                return;
            }

            time++;
        }
         
        System.out.println("KAKTUS");
    }

    static boolean spread_biber() {
        Deque<int[]> tmp = new ArrayDeque<>();

        while (!biber_deque.isEmpty()) {
            int[] cur_arr = biber_deque.pollFirst();
            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_row + dy[i];
                int new_col = cur_col + dx[i];
        
                if (isOutofBound(new_row, new_col)) continue;
                if (water_visited[new_row][new_col] || biber_visited[new_row][new_col]) continue;
                if (graph[new_row][new_col] == 'X') continue;
                if (graph[new_row][new_col] == 'D') {
                    return true;
                }

                tmp.add(new int[] {new_row, new_col, cur_arr[2] + 1});
                biber_visited[new_row][new_col] = true;
            }
        }

        biber_deque = tmp;
        return false;
    }
    
    static void spread_water() {
        Deque<int[]> tmp = new ArrayDeque<>();

        while (!water_deque.isEmpty()) {
            int[] cur_arr = water_deque.pollFirst();
            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];

            for (int i = 0; i < 4; i++) {
                int new_row = cur_row + dy[i];
                int new_col = cur_col + dx[i];
                
                if (isOutofBound(new_row, new_col)) continue;
                if (water_visited[new_row][new_col]) continue;
                if (graph[new_row][new_col] == 'D' || graph[new_row][new_col] == 'X') continue;

                tmp.add(new int[] {new_row, new_col});
                water_visited[new_row][new_col] = true;
            }
            
        }

        water_deque = tmp;
    }

    static boolean isOutofBound(int row, int col) {
        if (row < 0 || row == R || col < 0 || col == C) return true;
        return false;
    }
}
