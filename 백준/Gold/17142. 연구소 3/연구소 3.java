import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static ArrayList<Pos> virusList = new ArrayList<>();
    static int blankCnt;
    static int[][] graph;
    static int N, M;
    static Stack<Integer> stack = new Stack<>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer = Integer.MAX_VALUE; 
    
    static class Pos {
        int row, col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            
            for (int col = 0; col < N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());

                if (graph[row][col] == 2) virusList.add(new Pos(row, col));
                if (graph[row][col] == 0) blankCnt++;
            }
        }
        backtracking(0, 0);

        if (blankCnt == 0) {
            System.out.println(0);
            return;
        }
        
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    static void bfs() {
        boolean[][] visited = new boolean[N][N];
        Deque<int[]> deque = new ArrayDeque<>();
        int min_num = -1;
        int cnt = 0;

        for (int idx : stack) {
            Pos pos = virusList.get(idx);

            visited[pos.row][pos.col] = true;
            deque.add(new int[] {pos.row, pos.col, 0});

        }

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            if (cnt == blankCnt) break;
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[0] + dy[i];
                int new_col = cur_arr[1] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
                if (visited[new_row][new_col]) continue;
                if (graph[new_row][new_col] == 1) continue;

                if (graph[new_row][new_col] == 0) cnt++;
                
                int new_time = cur_arr[2] + 1;
                min_num = Math.max(min_num, new_time);
                deque.add(new int[] {new_row, new_col, new_time});
                visited[new_row][new_col] = true;
            }
        }

        
        if (cnt == blankCnt ) answer = Math.min(answer, min_num);
    }
    
    static void backtracking(int depth, int idx) {
        if (depth == M) {
            bfs();
            return;
        }

        for (int i = idx; i < virusList.size(); i++) {
            stack.push(i);
            backtracking(depth + 1, i + 1);
            stack.pop();
        }
    }
}