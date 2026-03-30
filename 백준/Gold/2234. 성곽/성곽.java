import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        
        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int[] bitArr = new int[] {1, 2, 4, 8};
    static int[] dy = new int[] {0, -1, 0, 1};
    static int[] dx = new int[] {-1, 0, 1, 0};
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt(); M = fs.nextInt();

        graph = new int[M][N];
        visited = new boolean[M][N];

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                graph[row][col] = fs.nextInt();
            }
        }

        int roomsCnt = 0;
        int maxSize = 0;
        int one = 0;
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                if (visited[row][col]) continue;

                roomsCnt++;
                maxSize = Math.max(maxSize, bfs(row, col));
            }
        }        

        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                int tmp = graph[row][col];

                for (int i = 0; i < 4; i++) {
                    int move_bit = graph[row][col];
                    boolean isMove = (move_bit & bitArr[i]) > 0;

                    if (isMove) {
                        visited = new boolean[M][N];
                        graph[row][col] ^= bitArr[i];
                        one = Math.max(one, bfs(row, col));
                        graph[row][col] = tmp;
                    }
                }
            }
        }
        
        System.out.println(roomsCnt);
        System.out.println(maxSize);
        System.out.println(one);
    }

    static int bfs(int row, int col) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {row, col});

        int result = 1;
        visited[row][col] = true;

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_row = cur_arr[0];
            int cur_col = cur_arr[1];
            int move_bit = 15 - graph[cur_row][cur_col];
           
            for (int i = 0; i < 4; i++) {
                boolean isMove = (move_bit & bitArr[i]) > 0;
                if (!isMove) continue;
                
                int new_row = cur_row + dy[i];
                int new_col = cur_col + dx[i];
                if (new_row < 0 || new_row == M || new_col < 0 || new_col == N) continue;

                if (visited[new_row][new_col]) continue;


                visited[new_row][new_col] = true;
                result++;
                deque.add(new int[] {new_row, new_col});
            }
        }

        return result;
    }
}