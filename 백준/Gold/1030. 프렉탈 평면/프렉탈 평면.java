import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int s, N, K;
    static int R1, R2;
    static int C1, C2;
    static int len;
    static int[][] ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());


        len = (int) Math.pow(N, s);
        ans = new int[R2 - R1 + 1][C2 - C1 + 1];

        
        
        recursion(0, 0, 0, len - 1, len - 1, false);

        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < R2 - R1 + 1; row++) {
            for (int col = 0; col < C2 - C1 + 1; col++) {
                sb.append(ans[row][col]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void recursion(int depth, int start_row, int start_col, int end_row, int end_col, boolean isBlack) {
        // 범위
        if ( start_row > R2  ) return;
        if (end_row < R1) return;
        if ( start_col >  C2 ) return;
        if (end_col < C1) return;
       
        if (depth == s) {
            
            ans[ start_row - R1  ][ start_col - C1 ] = isBlack ? 1 : 0;
            return;
        }
        int length = len / (int) Math.pow(N, depth + 1);
        
        // 중심좌표
        int black_start_row = (N - K) / 2;
        int black_end_row = black_start_row + K - 1;
        int black_start_col = (N - K) / 2;
        int black_end_col = black_start_col + K - 1;
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int new_start_row = start_row + row * length;
                int new_end_row = new_start_row + length - 1;
                int new_start_col = start_col + col * length;
                int new_end_col = new_start_col + length - 1;
                if (isBlack) {
                    recursion(depth + 1, new_start_row, new_start_col, new_end_row, new_end_col, true);
    
                    continue;
                }
                
                // 검은돌
                if (row >= black_start_row && row <= black_end_row &&
                   col >= black_start_col && col <= black_end_col) {
                    recursion(depth + 1, new_start_row, new_start_col, new_end_row, new_end_col, true);
                   } 
                else {
                    recursion(depth + 1, new_start_row, new_start_col, new_end_row, new_end_col, false);
                }
            }
        }
        
    }
    
}