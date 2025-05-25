import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static char[][] graph;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dy = new int[] {0, 0, 1, -1};
    static int[] dx = new int[] {1, -1, 0, 0};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        visited = new boolean[N][M];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < M; col++) {
                graph[row][col] = str.charAt(col);
            }
        }
        
        backtracking(0, 1);
        System.out.println(answer);
    }

    static void backtracking(int depth, int total) {
        
        if (depth == 2) {
            answer = Math.max(answer, total);
            
            return;
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (visited[row][col] || graph[row][col] == '.') continue;

                Stack<int[]> stack = new Stack<>();
                stack.push(new int[] {row, col});
                int[][] rangeArr = new int[4][2];

                for (int i = 0; i < 4; i++) {
                    rangeArr[i][0] = row + dy[i];
                    rangeArr[i][1] = col + dx[i];
                }
                
                while (true) {
                    Stack<int[]> tmp = new Stack<>();

                    for (int i = 0; i < 4; i++) {
                        int new_row = rangeArr[i][0];
                        int new_col = rangeArr[i][1];

                        if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M) continue;
                        if (visited[new_row][new_col]) continue;
                        if (graph[new_row][new_col] == '.') continue;

                        tmp.push(new int[] {new_row, new_col});
                        rangeArr[i][0] += dy[i];
                        rangeArr[i][1] += dx[i];
                    }

                    if (tmp.size() == 4) {
                        for (int[] arr : stack) visited[arr[0]][arr[1]] = true;
                        backtracking(depth + 1, total * stack.size());
                        for (int[] arr : stack) visited[arr[0]][arr[1]] = false;
                        while (!tmp.isEmpty()) stack.push(tmp.pop());
                    } else {
                        for (int[] arr : stack) visited[arr[0]][arr[1]] = true;
                        backtracking(depth + 1, total * stack.size());
                        for (int[] arr : stack) visited[arr[0]][arr[1]] = false;
                        break;
                    }
                }

                
                
            }
        }
    }
}