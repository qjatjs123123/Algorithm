import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static Stack<Integer> redStack = new Stack<>();
    static Stack<Integer> blueStack = new Stack<>();
    static ArrayList<int[]> list = new ArrayList<>();
    static int N, M, G, R;
    static int[][] graph;
    static boolean[] checkList;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer = 0;
    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt(); M = fs.nextInt(); G = fs.nextInt(); R = fs.nextInt();
        graph = new int[N][M];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                graph[row][col] = fs.nextInt();

                if (graph[row][col] == 2) list.add(new int[] {row, col});
            }
        }

        checkList = new boolean[list.size()];
        blue_backtracking(0, 0);
        System.out.println(answer);
    }

    static void blue_backtracking(int depth, int start) {
        if (depth == G) {
            red_backtracking(0, 0);
            return;
        }

        for (int i = start; i < list.size(); i++) {
            if (checkList[i]) continue;

            blueStack.push(i);
            checkList[i] = true;
            blue_backtracking(depth + 1, i + 1);
            checkList[i] = false;
            blueStack.pop();
        }
    }

    static void red_backtracking(int depth, int start) {
        if (depth == R) {
            bfs();
            return;
        }

        for (int i = start; i < list.size(); i++) {
            if (checkList[i]) continue;

            redStack.push(i);
            checkList[i] = true;
            red_backtracking(depth + 1, i + 1);
            checkList[i] = false;
            redStack.pop();
        }
    }

    static void bfs() {
        int[][] new_graph = new int[N][M];

        Deque<int[]> deque = new ArrayDeque<>();
        Deque<int[]> tmp = new ArrayDeque<>();
        for (int idx : blueStack) {
            deque.add(new int[] {list.get(idx)[0], list.get(idx)[1], 1, 1});
            new_graph[ list.get(idx)[0] ][ list.get(idx)[1] ] = 1;
        }

        for (int idx : redStack) {
            deque.add(new int[] {list.get(idx)[0], list.get(idx)[1], 1, -1});
            new_graph[ list.get(idx)[0] ][ list.get(idx)[1] ] = -1;
        }

        int result = 0;
        
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            if (new_graph[cur_arr[0]][cur_arr[1]] == Integer.MAX_VALUE) continue;
            int new_cnt = cur_arr[2] + 1;
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[0] + dy[i];
                int new_col = cur_arr[1] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (graph[new_row][new_col] == 0) continue;
                if (new_graph[new_row][new_col] == Integer.MAX_VALUE) continue;
                if (new_graph[new_row][new_col] != 0) {
                    if ( new_graph[new_row][new_col] + (new_cnt * cur_arr[3]) == 0 ) {
                        new_graph[new_row][new_col] = Integer.MAX_VALUE;
                        result++;
                    }
                    continue;
                }

                new_graph[new_row][new_col] = new_cnt * cur_arr[3];
                deque.add(new int[] {new_row, new_col, new_cnt, cur_arr[3]});
            }

        // if (result == 6) {
        //                         StringBuilder sb = new StringBuilder();
        // for (int row = 0; row < N; row++) {
        //     for (int col = 0; col < M; col++) {
        //         sb.append(new_graph[row][col] + " ");
        //     }
        //     sb.append("\n");
        // }
        // System.out.println(sb.toString());            
        // }

        }


        answer = Math.max(answer, result);
    }
}