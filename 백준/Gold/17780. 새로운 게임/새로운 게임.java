import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[][] graph;
    static Deque<Pos>[][] posGraph;
    static Deque<Pos>[] posArr;
    static int[] dy = new int[] {0, 0, -1, 1};
    static int[] dx = new int[] {1, -1, 0, 0};
    static boolean flg = false;
    static class Pos {
        int row, col, no, direct;

        Pos(int row, int col, int no, int direct) {
            this.row = row;
            this.col = col;
            this.no = no;
            this.direct = direct;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        posGraph = new Deque[N][N];
        posArr = new Deque[K];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                posGraph[i][j] = new ArrayDeque();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int direct = Integer.parseInt(st.nextToken()) - 1;

            Pos pos = new Pos(row, col, i, direct);
            posGraph[row][col].add(pos);
            posArr[i] = posGraph[row][col];
        }        

        int cnt = 0;
        while (true) {
            for (int i = 0; i < K; i++) {
                Deque<Pos> cur_deque = posArr[i];

                int floor_no = cur_deque.peekFirst().no;
                Pos floor_pos = cur_deque.peekFirst();
                
                if (floor_no != i) continue;

                int new_row = floor_pos.row + dy[floor_pos.direct];
                int new_col = floor_pos.col + dx[floor_pos.direct];

                controller(floor_pos, new_row, new_col);
            }
            
            if (++cnt >= 1_000) {
                System.out.println(-1);
                return;
            }

            if (flg) {
                System.out.println(cnt);
                return;
            }
        }
    }

    static void controller(Pos floor_pos, int new_row, int new_col) {
        if (isOutOfBounary(new_row, new_col)) {
            blueMove(floor_pos);
            return;
        }

        if (graph[new_row][new_col] == 2) blueMove(floor_pos);
        else if (graph[new_row][new_col] == 1) redMove(floor_pos, new_row, new_col);
        else whiteMove(floor_pos, new_row, new_col);
    }

    static void whiteMove(Pos floor_pos, int new_row, int new_col) {
        Deque<Pos> new_deque = posGraph[new_row][new_col];
        Deque<Pos> cur_deque = posGraph[floor_pos.row][floor_pos.col];

        while (!cur_deque.isEmpty()) {
            Pos pos = cur_deque.pollFirst();
            pos.row = new_row;
            pos.col = new_col;
            posArr[pos.no] = new_deque;
            
            new_deque.add(pos);
        }

        if (new_deque.size() >= 4) {
            flg = true;
        }
    }
    
    static void redMove(Pos floor_pos, int new_row, int new_col) {
        Deque<Pos> new_deque = posGraph[new_row][new_col];
        Deque<Pos> cur_deque = posGraph[floor_pos.row][floor_pos.col];

        while (!cur_deque.isEmpty()) {
            Pos pos = cur_deque.pollLast();
            pos.row = new_row;
            pos.col = new_col;
            posArr[pos.no] = new_deque;
            
            new_deque.add(pos);
        }

        if (new_deque.size() >= 4) {
            flg = true;
        }
    }
    
    static void blueMove(Pos floor_pos) {
        int new_direct = reverseDirect(floor_pos.direct);
        int new_row = floor_pos.row + dy[new_direct];
        int new_col = floor_pos.col + dx[new_direct];

        if (isOutOfBounary(new_row, new_col) || graph[new_row][new_col] == 2) {
            floor_pos.direct = new_direct;
        } else if(graph[new_row][new_col] == 1) {
            floor_pos.direct = new_direct;
            redMove(floor_pos, new_row, new_col);
        } else {
            floor_pos.direct = new_direct;
            whiteMove(floor_pos, new_row, new_col);
        }

        
    }

    static int reverseDirect (int direct) {
        if (direct == 0) return 1;
        else if (direct == 1) return 0;
        else if (direct == 2) return 3;
        else return 2;
    }
    
    static boolean isOutOfBounary(int new_row, int new_col) {
        if (new_row >= N || new_col >= N || new_row < 0 || new_col < 0) return true;
        return false;
    }
}