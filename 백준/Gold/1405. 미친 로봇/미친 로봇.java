import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static double[] percentArr = new double[4];
    static double answer = 0d;
    static int[] dy = new int[] {0, 0, 1, -1};
    static int[] dx = new int[] {1, -1, 0, 0};
    static boolean[][] visited = new boolean[100][100];
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            double d = Double.parseDouble(st.nextToken());
            percentArr[i] = d / 100;
        }

        visited[50][50] = true;
        dfs(0, 50, 50, 1d);

        System.out.println(answer);
    }

    static void dfs(int depth, int row, int col, double cur_percent) {
        if (depth == N) {
            answer += cur_percent;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int new_row = row + dy[i];
            int new_col = col + dx[i];

            if (new_row < 0 || new_row >= 100 || new_col < 0 || new_col >= 100) continue;
            if (visited[new_row][new_col]) continue;

            visited[new_row][new_col] = true;
            dfs(depth + 1, new_row, new_col, cur_percent * percentArr[i]);
            visited[new_row][new_col] = false;
        }
    }
}