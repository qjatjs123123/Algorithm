import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] memoryArr;
    static int[] costArr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memoryArr = new int[N + 1];
        costArr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= N; i++) {
            memoryArr[i] = Integer.parseInt(st.nextToken());    
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costArr[i] = Integer.parseInt(st.nextToken());    
        }

        int[][] dp = new int[101][10_001];
        int answer = Integer.MAX_VALUE;
        
        for (int row = 1; row <= N; row++) {
            int memory = memoryArr[row];
            int cost = costArr[row];
            
            if (row == 1) {
                dp[row][cost] = memory;
                continue;
            }

            
            
            for (int col = 0; col <= 10_000; col++) {
                dp[row][col] = dp[row - 1][col];
                if (col >= cost) {
                    dp[row][col] = Math.max(dp[row - 1][col - cost] + memory, dp[row - 1][col]);
                }
            }
        }

        for (int c = 0; c <= 10000; c++) {
            if (dp[N][c] >= M) {
                System.out.println(c);
                break;
            }
        }

        
    }
}