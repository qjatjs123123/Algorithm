import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int start_row, start_col, end_row, end_col;
    static int[][] graph;
    static int NUM = 5_000;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        start_row = Integer.parseInt(st.nextToken()) + NUM;
        start_col = Integer.parseInt(st.nextToken()) + NUM;
        end_row = Integer.parseInt(st.nextToken()) + NUM;
        end_col = Integer.parseInt(st.nextToken()) + NUM;

        graph = new int[end_row - start_row + 1][end_col - start_col + 1];

        int cur_row = NUM;
        int cur_col = NUM;

        int[] dy = new int[] {0, -1, 0, 1};
        int[] dx = new int[] {1, 0, -1, 0};
        int cnt = 2;
        int direct = 3;
        int tmp = 2;
        
        if (inArea(cur_row, cur_col)) graph[cur_row - start_row][cur_col - start_col] = 1;

        while (cnt <= 100_030_000) {
            direct = direct == 3 ? 0 : direct + 1;
            

            int loopCnt = tmp / 2;
            for (int i = 0; i < loopCnt; i++) {
                cur_row += dy[direct];
                cur_col += dx[direct];

                if (inArea(cur_row, cur_col)) {
                    graph[cur_row - start_row][cur_col - start_col] = cnt;    
                }

                cnt++;
            }
            tmp++;
        }
        display();
    }

    static void display() {
        StringBuilder sb = new StringBuilder();

        int max_len = 0;
        for (int row = 0; row < graph.length; row++) {
            
            
            for (int col = 0; col < graph[0].length; col++) {
                String str = Integer.toString(graph[row][col]);

                max_len = Math.max(max_len, str.length());
            }

        }

        for (int row = 0; row < graph.length; row++) {
            
            for (int col = 0; col < graph[0].length; col++) {
                String str = Integer.toString(graph[row][col]);

                int blank_cnt = max_len - str.length();

                for (int i = 0; i < blank_cnt; i++) sb.append(" ");
                sb.append(str).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    static boolean inArea(int row, int col) {
        if (start_row <= row &&
           end_row >= row && 
           start_col <= col &&
           end_col >= col) return true;
        return false;
    }
}