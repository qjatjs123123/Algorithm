import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static char[][] graph;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        dp = new int[N][M];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < M; col++) graph[row][col] = str.charAt(col);
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                dp[row][col] = -1;
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (dp[row][col] != -1) continue;
                
                Stack<int[]> stack = new Stack<>();
                
                int cur_row = row;
                int cur_col = col;
                int answer = 2;
                
                while (true) {
                    // 밖으로
                    if (cur_row < 0 || cur_row == N || cur_col < 0 || cur_col == M) {
                        answer = 1;
                        break;
                    } 
                    
                    stack.push(new int[] {cur_row, cur_col});
                    
                    if (dp[cur_row][cur_col] == 1) {
                        answer = 1;
                        break;
                    } else if (dp[cur_row][cur_col] == 2 || dp[cur_row][cur_col] == 0) {
                        answer = 0;
                        break;
                    }

                    dp[cur_row][cur_col] = 2;  
                    
                    if (graph[cur_row][cur_col] == 'U') cur_row--;
                    else if(graph[cur_row][cur_col] == 'D') cur_row++;
                    else if(graph[cur_row][cur_col] == 'R') cur_col++;
                    else cur_col--;
                    
                        
                }

                while (!stack.isEmpty()) {
                    int[] cur_arr = stack.pop();

                    dp[cur_arr[0]][cur_arr[1]] = answer;
                }

                
            }
        }
        
        int result = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) result += dp[row][col];
        }

        StringBuilder sb = new StringBuilder();

        System.out.println(result);
    }
}