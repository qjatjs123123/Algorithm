import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] direction = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    static Deque<int[]> list;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        int count = 0;
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
                if (graph[row][col] == 1) count++;
            }
        }

        visited = new boolean[N][M];

        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[] {0, 0});
        visited[0][0] = true;
        list = new LinkedList<>();
        
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];

            for (int[] direct : direction) {
                int new_row = cur_row + direct[0];
                int new_col = cur_col + direct[1];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (visited[new_row][new_col]) continue;

                visited[new_row][new_col] = true;
                if (graph[new_row][new_col] == 1) {
                    list.add(new int[] {new_row, new_col});
                } else {
                    deque.add(new int[] {new_row, new_col});
                }
            }
            
        }

        int ans = 1;
        int prev = count;
        while (true) {
            count -= list.size();

            if (count == 0) break;
            ans++;
            bfs();
            prev = count;
        }

        System.out.println(ans);
        System.out.println(prev);
    }

    static void bfs() {
        Deque<int[]> deque = new LinkedList<>();

        
        while (!list.isEmpty()) {
            int[] cur_arr = list.pollFirst();
            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];

            
            for (int[] direct : direction) {
                int new_row = cur_row + direct[0];
                int new_col = cur_col + direct[1];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (visited[new_row][new_col]) continue;

                visited[new_row][new_col] = true;
                if (graph[new_row][new_col] == 1) {
                    deque.add(new int[] {new_row, new_col});
                } else{
                    list.add(new int[] {new_row, new_col});
                } 
                
            }
            
        }

        list = deque;
    }
    
}