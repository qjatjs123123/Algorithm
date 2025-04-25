import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static char[][] graph;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] dp;
    static boolean[][] visited;
    static HashMap<Integer, int[]> dict = new HashMap<>();
    static Deque<int[]> deque = new LinkedList<>();
    
    static class Pos implements Comparable<Pos>{
        int row, col, count, direct;

        Pos(int row, int col, int count, int direct) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.direct = direct;
        }

        public String toString() {
            return this.row + " " + this.col + " " + this.count + " " + this.direct;
        }

        @Override
        public int compareTo(Pos o) {
            return this.count - o.count;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new char[N][N];
        visited = new boolean[N][N];
        dp = new int[N][N];
        Pos init_pos = null;
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();
            for (int col = 0; col < N; col++) {
                graph[row][col] = str.charAt(col);

                dp[row][col] = Integer.MAX_VALUE;
                // dp[row][col] = 9;
                
                if (graph[row][col] == '#') {
                    init_pos = new Pos(row, col, 0, 0);
                }
            }      
        }

        
        dp[init_pos.row][init_pos.col] = 0;
        dict.put(0, new int[] {0, 1, 2, 3});
        dict.put(1, new int[] {0, 1});
        dict.put(2, new int[] {2, 3});
        
        deque.add(new int[] {init_pos.row, init_pos.col, 0, 0});
        boolean flg = false;
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int[] direct_arr = dict.get(cur_arr[3]); 
            
            for (int direct : direct_arr) {
                flg = move(cur_arr[0], cur_arr[1], cur_arr[2], direct);
                if (flg) break;
            }
            if (flg) break;
        }

        
    }

    static void display() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append(dp[row][col] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static boolean move(int new_row, int new_col, int count, int direct) {
        
        while (true) {
            new_row += dy[direct];
            new_col += dx[direct];
            
            if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) break;
            if (dp[new_row][new_col] <= count) continue;
            if (graph[new_row][new_col] == '*') break;
            if (graph[new_row][new_col] == '!') {
                if (direct < 2) deque.add(new int[] {new_row, new_col, count + 1, 2});
                else deque.add(new int[] {new_row, new_col, count + 1, 1});
            }
            
            if (graph[new_row][new_col] == '#') {
                System.out.println(count);
                return true;
            }
            dp[new_row][new_col] = count;
        }
        return false;
    }
}