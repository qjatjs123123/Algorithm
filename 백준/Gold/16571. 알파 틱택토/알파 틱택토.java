import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class TicTakToe {
        int[][] board;
        int myTurn;
        int[][] dy = new int[][] { 
            { 0, 1, 2 }, 
            { 0, 0, 0 },
            { 0, 1, 2 },
            { 2, 2, 2 },
            { 0, 1, 2 },
            { 0, 1, 2 },
            { 1, 1, 1 },
            { 0, 1, 2 }
        };
        int[][] dx = new int[][] {
            { 0, 0, 0 },
            { 0, 1, 2 },
            { 2, 2, 2 },
            { 0, 1, 2 },
            { 0, 1, 2 },
            { 2, 1, 0 },
            { 0, 1, 2 },
            { 1, 1, 1 }
        };

        
        TicTakToe(int[][] board) {
            this.board = board;
            int oneCnt = 0;
            int twoCnt = 0;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 1) oneCnt++;
                    if (board[row][col] == 2) twoCnt++;
                }
            }

            if (oneCnt == twoCnt) myTurn = 1;
            else myTurn = 2;
        }

        int isGameOver() {
            for (int i = 0; i < 8; i++) {
                int init = board[ dy[i][0] ][ dx[i][0] ];
                int cnt = 0;
                
                if (init == 0) continue;
                for (int j = 0; j < 3; j++) {
                    if ( init == board[ dy[i][j] ][ dx[i][j] ] ) cnt++;
                }

                if (cnt == 3) {
                    if (myTurn == init) return 1;
                    else return -1;
                }
            }

            int totalCnt = 0;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] != 0) totalCnt++;
                }
            }

            if (totalCnt == 9) return 0;
            else return -2;
        }

        void start() {
            int result = dfs(myTurn, 0);

            if (result == 1) System.out.println("W");
            else if(result == 0) System.out.println("D");
            else System.out.println("L");
        }
        
        int dfs (int turn, int cnt) {
            int gameOver = isGameOver();
            
            if (gameOver != -2) return gameOver;
            boolean isMyTurn = (turn == myTurn);

            int result;
            if (isMyTurn) result = Integer.MIN_VALUE;
            else result = Integer.MAX_VALUE;

            int newTurn = turn == 1 ? 2 : 1;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] != 0) continue;

                    board[row][col] = turn;
                    
                    if (isMyTurn) result = Math.max(result, dfs(newTurn, 1));
                    else result = Math.min(result, dfs(newTurn, 1));
                    
                    board[row][col] = 0;
                    
                }
            }

            return result;
        }
        
        void display() {
            StringBuilder sb = new StringBuilder();

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    sb.append(board[row][col]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());
        }
    }
    
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
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        int[][] board = new int[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = fs.nextInt();
            }
        }

        TicTakToe tictaktoe = new TicTakToe(board);
        tictaktoe.start();
    }

}