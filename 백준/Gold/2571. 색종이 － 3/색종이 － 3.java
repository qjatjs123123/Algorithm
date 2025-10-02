import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static final int BOARD_SIZE = 101;
    static final int PAPER_SIZE = 10;
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

    static class Board {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

        Board(FastScanner fs) throws IOException{
            for (int i = 0; i < N; i++){
                int row = fs.nextInt();
                int col = fs.nextInt();

                put(row, col);
            }
        }

        void put(int row, int col) {
            for (int r = row; r < row + PAPER_SIZE; r++) {
                for (int c = col; c < col + PAPER_SIZE; c++) {
                    board[r][c] = 1;
                }
            }
        }

        int maxRect() {
            int result = 0;

            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (board[row][col] == 0) continue;

                    for (int eRow = row + 1; eRow < BOARD_SIZE; eRow++) {
                        for (int eCol = col + 1; eCol < BOARD_SIZE; eCol++) {
                            int tmpRect = (eRow - row) * (eCol - col);

                            if (result >= tmpRect) continue;
                            if (check(row, col, eRow, eCol) == false) continue;

                            result = Math.max(result, tmpRect);
                        }
                    }
                }
            }

            return result;
        }

        boolean check(int sRow, int sCol, int eRow, int eCol) {
            for (int r = sRow; r < eRow; r++) {
                for (int c = sCol; c < eCol; c++) {
                    if (board[r][c] == 0) return false;
                }
            }

            return true;
        }
        
        void display() {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) sb.append(board[row][col]).append(" ");
                sb.append("\n");
            }

            System.out.println(sb.toString());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();

        Board br = new Board(fs);
        System.out.println(br.maxRect());
    }
}