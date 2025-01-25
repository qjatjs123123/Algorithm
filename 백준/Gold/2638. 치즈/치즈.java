import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int CHEEZE_CNT = 0;
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};

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

        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());

                if (graph[row][col] == 1) CHEEZE_CNT++;
            }
        }

        int time = 0;
        int cur_cnt = 0;
        
        while (cur_cnt < CHEEZE_CNT) {
            visited = new boolean[N][M];

            ArrayList<Pos> boundaryList = getBoundary();
            ArrayList<Pos> validBoundaryList = getValidBoundary(boundaryList);
            removeCheeze(validBoundaryList);
            
            cur_cnt += validBoundaryList.size();    
            time++;
        }

        System.out.println(time);
        
    }

    static void removeCheeze(ArrayList<Pos> validBoundaryList) {
        for (Pos pos : validBoundaryList) {
            graph[pos.row][pos.col] = 0;
        }
    }
    
    static ArrayList<Pos> getValidBoundary(ArrayList<Pos> boundaryList) {
        ArrayList<Pos> validBoundaryList = new ArrayList<>();

        for (Pos pos : boundaryList) {
            int cnt = 0;
    
            for (int i = 0; i < 4; i++) {
                int new_row = pos.row + dy[i];
                int new_col = pos.col + dx[i];

                if (new_row < 0 || new_row == N ||
                   new_col < 0 || new_col == M) continue;

                if (graph[new_row][new_col] == 0 && visited[new_row][new_col]) cnt++;
            }

            if (cnt >= 2) validBoundaryList.add(new Pos(pos.row, pos.col));
        }

        return validBoundaryList;
    }
    
    static void display(ArrayList<Pos> list) {
        int[][] g = new int[N][M];

        for (Pos pos : list) {
            g[pos.row][pos.col] = 1;
        }

        for (int[] a : g) {
            System.out.println(Arrays.toString(a));
        }
    }
    
    static ArrayList<Pos> getBoundary() {
        ArrayList<Pos> boundaryList = new ArrayList<>();

        Deque<Pos> deque = new LinkedList<>();
        deque.add(new Pos(0, 0));
        visited[0][0] = true;
        
        while (!deque.isEmpty()) {
            Pos cur_pos = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                int new_row = cur_pos.row + dy[i];
                int new_col = cur_pos.col + dx[i];

                if (new_row < 0 || new_row == N ||
                   new_col < 0 || new_col == M) continue;
                if (visited[new_row][new_col]) continue;

                if (graph[new_row][new_col] == 0) deque.add(new Pos(new_row, new_col));
                else boundaryList.add(new Pos(new_row, new_col));

                visited[new_row][new_col] = true;
            }
        }

        return boundaryList;
    }
}