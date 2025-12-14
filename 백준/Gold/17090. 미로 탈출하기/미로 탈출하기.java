import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static char[][] graph;
    static int[][] dp;
    static boolean[][] visited;
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt(); 
        M = fs.nextInt();

        graph = new char[N][M];
        for (int row = 0; row < N; row++) {
            String str = fs.next();

            for (int col = 0; col < M; col++) graph[row][col] = str.charAt(col);
        }
        
        dp = new int[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) dp[row][col] = -1;
        }
        visited = new boolean[N][M];

        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                visited[row][col] = true;

                int result = dfs(row, col);
                if (result != -1) answer += result;
                visited[row][col] = false;
            }
        }

        System.out.println(answer);
    }

    static int dfs(int row, int col) {
        if (dp[row][col] != -1) return dp[row][col];

        int result = 1;
        int new_row = row;
        int new_col = col;
        
        if (graph[row][col] == 'U') new_row--;
        else if (graph[row][col] == 'D') new_row++;
        else if (graph[row][col] == 'L') new_col--;
        else new_col++;

        if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M)  return 1;
        if (visited[new_row][new_col]) return 0;
        
        visited[new_row][new_col] = true;
        result *= dfs(new_row, new_col);
        visited[new_row][new_col] = false;

        dp[row][col] = result;
        return result;
    }
}