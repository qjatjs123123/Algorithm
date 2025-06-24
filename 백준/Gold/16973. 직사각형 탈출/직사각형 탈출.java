import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int H, W, Sr, Sc, Fr, Fc;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sr = Integer.parseInt(st.nextToken()) - 1;
        Sc = Integer.parseInt(st.nextToken()) - 1;
        Fr = Integer.parseInt(st.nextToken()) - 1;
        Fc = Integer.parseInt(st.nextToken()) - 1;


        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {Sr, Sc, 0});
        visited[Sr][Sc] = true;
    
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            int sr = cur_arr[0];
            int sc = cur_arr[1];
            int fr = sr + H - 1;
            int fc = sc + W - 1;

            if (isInclude(sr, sc, fr, fc)) {
                System.out.println(cur_arr[2]);
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int new_sr = sr + dy[i];
                int new_sc = sc + dx[i];
                int new_fr = fr + dy[i];
                int new_fc = fc + dx[i];

                if (isOutofBound(new_sr, new_sc) || isOutofBound(new_fr, new_fc)) continue;
                if (visited[new_sr][new_sc]) continue;
                if (i == 0 && !isRow(new_fr, new_sc, new_fc)) continue;
                if (i == 1 && !isRow(new_sr, new_sc, new_fc)) continue;
                if (i == 2 && !isCol(new_fc, new_sr, new_fr)) continue;
                if (i == 3 && !isCol(new_sc, new_sr, new_fr)) continue;

                visited[new_sr][new_sc] = true;
                deque.add(new int[] {new_sr, new_sc, cur_arr[2] + 1});
            }
        }

        System.out.println(-1);
    }

    static boolean isCol(int new_col, int sr, int fr) {
        for (int r = sr; r <= fr; r++) {
            if (graph[r][new_col] == 1) return false;
        }

        return true;
    }
    
    static boolean isRow(int new_row, int sc, int fc) {
        for (int c = sc; c <= fc; c++) {
            if (graph[new_row][c] == 1) return false;
        }

        return true;
    }
    
    static boolean isInclude(int sr, int sc, int fr, int fc) {
        if (sr == Fr && sc == Fc) return true;
        return false;
    }
    
    static boolean isOutofBound(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M) return true;
        return false;
    }
}