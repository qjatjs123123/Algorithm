import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int w, h;
    static int[][][][] dp;
    static int[][] direction = {
        {0, 1},
        {1, 0}
    };
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        dp = new int[h][w][2][2];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int z = 0; z < 2; z++){
                        dp[i][j][k][z] = -1;
                    }
                }
            }
        }

        System.out.println((dfs(1,0,1,1) + dfs(0, 1, 0, 0))%100000);
    }

    static int dfs(int cur_row, int cur_col, int prev_direct, int cur_direct) {
        if (dp[cur_row][cur_col][prev_direct][cur_direct] != -1) return dp[cur_row][cur_col][prev_direct][cur_direct];
        if (cur_row == h - 1 && cur_col == w - 1) {
            return 1;
        }

        int total = 0;
        if (prev_direct == cur_direct) {
            for (int i = 0; i < 2; i++) {
                int new_row = cur_row + direction[i][0];
                int new_col = cur_col + direction[i][1];

                if (new_row >= h || new_col >= w) continue;

                total += dfs(new_row, new_col, cur_direct, i);
            }
        } else {
            
            int new_row = cur_row + direction[cur_direct][0];
            int new_col = cur_col + direction[cur_direct][1];

            if (new_row < h && new_col < w) {
                 total += dfs(new_row, new_col, cur_direct, cur_direct);
            }
            
        }
        
        dp[cur_row][cur_col][prev_direct][cur_direct] = total % 100000;
        return total;
    }
}