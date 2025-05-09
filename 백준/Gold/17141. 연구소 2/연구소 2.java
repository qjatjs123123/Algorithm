import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static ArrayList<int[]> virusArr = new ArrayList<>();
    static int blankCnt = 0;
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            
            for (int col = 0; col < N; col++) {
                int num = Integer.parseInt(st.nextToken());
                graph[row][col] = num;
                
                if (graph[row][col] == 2) {
                    virusArr.add(new int[] {row, col});
                } else if (graph[row][col] == 0) {
                    blankCnt++;
                }
            }
        }

        visited = new boolean[virusArr.size()];
        backtracking(0, 0);

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
        
    }

    static void bfs() {
        boolean[][] visited_graph = new boolean[N][N];
        Deque<int[]> deque = new ArrayDeque<>();

        int total = 0;
        int min_num = 0;
        for (int idx : stack) {
            int[] tmp = virusArr.get(idx);
            deque.add(new int[] {tmp[0], tmp[1], 0});
            visited_graph[tmp[0]][tmp[1]] = true;
        }

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[0] + dy[i];
                int new_col = cur_arr[1] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
                if (graph[new_row][new_col] == 1) continue;
                if (visited_graph[new_row][new_col]) continue;

                total++;
                visited_graph[new_row][new_col] = true;
                deque.add(new int[] {new_row, new_col, cur_arr[2] + 1});
                min_num = Math.max(min_num, cur_arr[2] + 1);
            }
        }

        if (total == (blankCnt + virusArr.size() - M)) {
            answer = Math.min(answer, min_num);
        }
    }
    
    static void backtracking(int depth, int idx) {
        if (depth == M) {
            // System.out.println(stack);
            bfs();
            return;
        }

        for (int i = idx; i < virusArr.size(); i++) {
            if (visited[i]) continue;

            stack.push(i);
            visited[i] = true;
            backtracking(depth + 1, i + 1);
            visited[i] = false;
            stack.pop();
        }
    }
}