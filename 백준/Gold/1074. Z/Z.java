import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, R, C;
    static int answer = 0;
    static int[] drow = new int[] {0, 0, 1, 1};
    static int[] dcol = new int[] {0, 1, 0, 1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int num = (int)Math.pow(2, N);
        recursion(0, 0, num, num);
        System.out.println(answer);
    }

    static void recursion(int start_row, int start_col, int end_row, int end_col) {
        if (start_row == R && start_col == C) {
            return;
        }
 
        int len = end_row - start_row;
        int half = len / 2;

        
        int total = 0;  
        for (int i = 0; i < 4; i++) {
            int new_start_row = start_row + drow[i] * half;
            int new_start_col = start_col + dcol[i] * half;
            int new_end_row = start_row + half + drow[i] * half;
            int new_end_col = start_col + half + dcol[i] * half;

            if (new_start_row <= R && new_end_row > R &&
               new_start_col <= C && new_end_col > C) {
                recursion(new_start_row, new_start_col, new_end_row, new_end_col);
                break;
               }

            total += half * half;
        }

        answer += total;
    }
}