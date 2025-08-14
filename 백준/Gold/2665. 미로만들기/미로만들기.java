import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        visited = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < N; col++) {
                graph[row][col] = str.charAt(col) - '0';
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[] {0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();

            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[1] + dy[i];
                int new_col = cur_arr[2] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
                if (visited[new_row][new_col]) continue;
                if (new_row == N - 1 && new_col == N - 1) {
                    System.out.println(cur_arr[0]);
                    return;
                }

                visited[new_row][new_col] = true;
                if (graph[new_row][new_col] == 0) {
                    pq.add(new int[] {cur_arr[0] + 1, new_row, new_col});
                } else {
                    pq.add(new int[] {cur_arr[0], new_row, new_col});
                }
            }
        }
    }
}