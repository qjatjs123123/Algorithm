import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] graph = new int[8][7];
    static boolean[][] dp = new boolean[7][7];
    static boolean[][] visited = new boolean[8][7];
    static int answer = 0;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int row = 0; row < 8; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < 7; col++) {
                graph[row][col] = str.charAt(col) - '0';
            }
        }

        backtracking(0, 0);

        System.out.println(answer);
    }

    static void backtracking(int row, int col) {
        if (row == 8) {
            answer++;
            return;
        }
    
        if (col >= 7) {
            backtracking(row + 1, 0);
            return;
        }
    
        if (visited[row][col]) {
            backtracking(row, col + 1);
            return;
        }
    
        for (int d = 0; d < 4; d++) {
            int new_row = row + dy[d];
            int new_col = col + dx[d];
    
            if (new_row < 0 || new_col < 0 || new_row >= 8 || new_col >= 7) continue;
            if (visited[new_row][new_col]) continue;
    
            int a = graph[row][col];
            int b = graph[new_row][new_col];
            if (dp[a][b] || dp[b][a]) continue;
    
            dp[a][b] = dp[b][a] = true;
            visited[row][col] = true;
            visited[new_row][new_col] = true;
    
            backtracking(row, col + 1);
    
            dp[a][b] = dp[b][a] = false;
            visited[row][col] = false;
            visited[new_row][new_col] = false;
        }
    }

}