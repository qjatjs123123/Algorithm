import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] rectCountArr = new int[] {5, 5, 5, 5, 5, 5};
    static int[][] graph = new int[10][10];
    static int count = 0;
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int row = 0; row < 10; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < 10; col++) graph[row][col] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0);

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
        
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < 10; i++) {
        //     for (int j = 0; j < 10; j++) sb.append(graph[i][j]).append(" ");
        //     sb.append("\n");
        // }
        // System.out.println(sb.toString());
    }

    static void backtracking(int row, int col) {
        if (row == 10) {
            answer = Math.min(answer, count);
            return;
        }

        if (col == 10) {
            backtracking(row + 1, 0);
            return;
        }


        if (graph[row][col] == 1) {
            boolean flg = false;
            for (int len = 1; len <= 5; len++) {
                if (rectCountArr[len] == 0) continue;
                boolean result = isRect(row, col, len);
  
                if (result) {
                    rectCountArr[len]--;
                    change(row, col, len, 0);
                    count++;
                    flg = true;
                    backtracking(row, col + 1);
                    rectCountArr[len]++;
                    change(row, col, len, 1);
                    count--;

                }
            }
            
        } else {
            backtracking(row, col + 1);
        }
    }

    static void change(int row, int col, int len, int num) {
        int end_row = row + len;
        int end_col = col + len;

        for (int r = row; r < end_row; r++) {
            for (int c = col; c < end_col; c++) {
                graph[r][c] = num;
            }
        }
    }
    
    static boolean isRect(int row, int col, int len) {
        int end_row = row + len;
        int end_col = col + len;

        if (end_row > 10 || end_col > 10) return false;

        for (int r = row; r < end_row; r++) {
            for (int c = col; c < end_col; c++) {
                if (graph[r][c] == 0) return false;
            }
        }

        return true;
    }
}