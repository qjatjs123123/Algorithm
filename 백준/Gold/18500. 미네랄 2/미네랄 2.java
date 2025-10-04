import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int R, C;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        
    }

    static class Mineral {
        int row, col;
        boolean isMineral, isCluster;

        Mineral (int row, int col, boolean isMineral, boolean isCluster) {
            this.row = row;
            this.col = col;
            this.isMineral = isMineral;
            this.isCluster = isCluster;
        }
    }
    
    static class Board{
        Mineral[][] board = new Mineral[R][C];

        Board(FastScanner fs) throws IOException{
            for (int row = 0; row < R; row++) {
                String str = fs.next();

                for (int col = 0; col < C; col++) {
                    char c = str.charAt(col);

                    if (c == 'x') create(row, col, true, false);
                    else create(row, col, false, false);
                }
            }
        }

        void throwFromLeft(int h) {
            int height = R - h;

            for (int c = 0; c < C; c++) {
                if (board[height][c].isMineral) {
                    destory(height, c);
                    break;
                }
            }
        }

        void throwFromRight(int h) {
            int height = R - h;

            for (int c =  C - 1; c >= 0; c--) {
                if (board[height][c].isMineral) {
                    destory(height, c);
                    break;
                }
            }
        }

        void divide() {
            boolean[][] visited = new boolean[R][C];

            for (int r = R - 1; r >= 0; r--) {
                for (int c = 0; c < C; c++) {
                    if (visited[r][c] || !board[r][c].isMineral) continue;

                    bfs(r, c, visited);
                }
            }
            
        }

        ArrayList<Mineral> getCluster() {
            ArrayList<Mineral> list = new ArrayList<>();

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (board[r][c].isCluster) list.add(new Mineral(r, c, true, true));
                }
            }

            return list;
        }
        
        void bfs(int r, int c, boolean[][] visited) {
            Deque<int[]> deque = new ArrayDeque<>();
            deque.add(new int[] {r, c});
            Stack<int[]> stack = new Stack<>();
            
            visited[r][c] = true;
            boolean flg = false;
            while (!deque.isEmpty()) {
                int[] cur_arr = deque.pollFirst();
                int cur_row = cur_arr[0];
                int cur_col = cur_arr[1];
                
                if (cur_row == R - 1) flg = true;
                stack.push(new int[] {cur_row, cur_col});
                
                for (int i = 0; i < 4; i++) {
                    int new_row = cur_row + dy[i];
                    int new_col = cur_col + dx[i];

                    if (isOutofBoundary(new_row, new_col) || 
                        visited[new_row][new_col] ||
                        board[new_row][new_col].isMineral == false) continue;

                    deque.add(new int[] {new_row, new_col});
                    visited[new_row][new_col] = true;
                }
            }

            if (!flg) {
                while(!stack.isEmpty()) {
                    int[] arr = stack.pop();

                    board[ arr[0] ][ arr[1] ].isCluster = true;
                }
            }
        }

        void drop(ArrayList<Mineral> list) {
            while (true) {
                if (isDrop(list) == false) break;

                for (Mineral m : list) {
                    destory(m.row, m.col);
                }

                for (Mineral m : list) {
                    m.row += 1;
                    create(m.row, m.col, true, true);
                }
            }
        }

        boolean isDrop(ArrayList<Mineral> list) {

            for (Mineral m : list) {
                int new_row = m.row + 1;
                int new_col = m.col;

                if(new_row == R) return false;
                if (board[new_row][new_col].isMineral && !board[new_row][new_col].isCluster) return false;
                
            }

            return true;
        }

        void init() {
            for (int row = 0; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    if (board[row][col].isCluster) board[row][col].isCluster = false;
                }
            }
        }
        
        void create(int row, int col, boolean isMineral, boolean isCluster) {
            board[row][col] = new Mineral(row, col, isMineral, isCluster);
        }

        void destory(int row, int col) {
            board[row][col].isMineral = false;
            board[row][col].isCluster = false;
        }
        
        void display() {
            StringBuilder sb = new StringBuilder();

            for (int row = 0; row < R; row++) {
                for (int col = 0; col < C; col++) {
                    if (board[row][col].isMineral) sb.append("x");
                    else sb.append(".");
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
        }
    }

    static boolean isOutofBoundary(int r, int c) {
        if (r < 0 || r == R || c < 0 || c == C) return true;
        return false;
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        R = fs.nextInt();
        C = fs.nextInt();

        Board bd = new Board(fs);

        int N = fs.nextInt();
        for (int i = 0; i < N; i++) {
            bd.init();
            int height = fs.nextInt();

            if (i % 2 == 0) {
                bd.throwFromLeft(height);
            } else {
                bd.throwFromRight(height);
            }

            bd.divide();
            ArrayList<Mineral> list = bd.getCluster();

            if (list.isEmpty()) continue;

            bd.drop(list);
            
        }

        bd.display();
    }
}