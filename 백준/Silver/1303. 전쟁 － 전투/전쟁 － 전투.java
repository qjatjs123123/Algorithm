import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static char[][] graph;
    static int N, M;
    static boolean[][] visited;
    static int[][] direction = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[M][N];
        visited = new boolean[M][N];
        
        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int col = 0; col < N; col++) {
                graph[row][col] = str.charAt(col);
            }
        }

        int blue = 0;
        int white = 0;
    
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                if (!visited[row][col]) {
                    if (graph[row][col] == 'W') white += bfs(row, col);
                    else blue += bfs(row, col);
                }
            }
        }

        System.out.println(white + " " + blue);
    }

    static int bfs(int row, int col) {
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[] {row, col});
        int ans = 1;
        visited[row][col] = true;

        while(!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];

            for (int[] direct : direction) {
                int new_row = cur_row + direct[0];
                int new_col = cur_col + direct[1];

                if (new_row < 0 || new_row == M
                   || new_col < 0 || new_col == N) continue;

                if (visited[new_row][new_col] || graph[new_row][new_col] != graph[row][col]) continue;

                visited[new_row][new_col] = true;
                deque.add(new int[] {new_row, new_col});
                ans++;
            }
            
        }

        return ans * ans;
    }
}