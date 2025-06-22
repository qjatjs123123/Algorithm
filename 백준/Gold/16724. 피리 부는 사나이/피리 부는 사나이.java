import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int cycleNum = 1;
    static int[][] visited;
    static char[][] graph;
    static Stack<int[]> stack = new Stack<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        visited = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int j = 0; j < M; j++) graph[i][j] = str.charAt(j);
        }        

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (visited[row][col] != 0) continue;
                
                stack = new Stack<>();
                stack.push(new int[] {row, col});
                visited[row][col] = cycleNum;

                dfs(row, col);
            }
        }
        
        System.out.println(cycleNum - 1);
    }

    static void dfs(int row, int col) {
        int new_row = row;
        int new_col = col;

        if (graph[row][col] == 'D') new_row += 1;
        else if(graph[row][col] == 'U') new_row -= 1;
        else if(graph[row][col] == 'R') new_col += 1;
        else new_col -= 1;

        if (visited[new_row][new_col] == 0) {
            stack.push(new int[] {new_row, new_col});
            visited[new_row][new_col] = cycleNum;
            dfs(new_row, new_col);
        } else if (visited[new_row][new_col] == cycleNum) {
            cycleNum++;
            return;
        }else {
            while (!stack.isEmpty()) {
                int[] cur_arr = stack.pop();

                visited[cur_arr[0]][cur_arr[1]] = visited[new_row][new_col];
            }
        }
    }
}