import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int dy[] = {1, -1, 0, 0, 2, 1, 2, 1, -1, -2, -1, -2};
    static int dx[] = {0, 0, 1, -1, 1, 2, -1, -2, 2, 1, -2, -1};

    static class Pos {
        int row, col, k, cnt;

        Pos(int row, int col, int k, int cnt) {
            this.row = row;
            this.col = col;
            this.k = k;
            this.cnt = cnt;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] graph = new int[H][W];
        boolean[][][] visited = new boolean[H][W][K + 1];
        
        for (int row = 0; row < H; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < W; col++) graph[row][col] = Integer.parseInt(st.nextToken());
        }

        Deque<Pos> deque = new LinkedList<>();
        deque.add(new Pos(0, 0, K, 0));

        int ans = -1;
        while (!deque.isEmpty()) {
            Pos cur_pos = deque.pollFirst();

            if (cur_pos.row == H - 1 && cur_pos.col == W - 1) {
                ans = cur_pos.cnt;
                break;
            }
            
            for (int i = 0; i < 12; i++) {
                if (cur_pos.k == 0 && i >= 4) break;
                int new_row = cur_pos.row + dy[i];
                int new_col = cur_pos.col + dx[i];
                int new_k = i >= 4 ? cur_pos.k - 1 : cur_pos.k;
                
                if (new_row < 0 || new_row >= H || new_col < 0 || new_col >= W) continue;
                if (visited[new_row][new_col][new_k]) continue;
                if (graph[new_row][new_col] == 1) continue;


                if (i < 4) {
                    deque.add(new Pos(new_row, new_col, new_k, cur_pos.cnt + 1));
                } else {
                    deque.add(new Pos(new_row, new_col, new_k, cur_pos.cnt + 1));
                }

                visited[new_row][new_col][new_k] = true;
            }
        }

        System.out.println(ans);
    }
}