import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int R, C;
    static char[][] graph;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    
    static class Pos implements Comparable<Pos>{
        int row, col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.row == o.row) return this.col - o.col;
            return  o.row - this.row;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        
        for (int row = 0; row < R; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < C; col++) {
                graph[row][col] = str.charAt(col);     
            }
        }

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        for (int n = 0; n < N; n++) {
            int height = Integer.parseInt(st.nextToken());

            if (n % 2 == 0) removeLeftToRight(height);
            else removeRightToLeft(height);

            
            controller();
            
        }
        display();
    }

    static void controller() {
        boolean[][] visited = new boolean[R][C];
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (visited[row][col] || graph[row][col] == '.') continue;

                PriorityQueue<Pos> result = bfs(visited, row, col);
                if (result != null) pq = result;
            }
        }

        if (!pq.isEmpty())
            moveDown(pq);
        
    }

    static void moveDown(PriorityQueue<Pos> pq) {
        boolean flg = false;
        PriorityQueue<Pos> backupQueue = new PriorityQueue<>();
        for (Pos pos : pq) backupQueue.add(new Pos(pos.row, pos.col));
        PriorityQueue<Pos> progressQueue = new PriorityQueue<>();

        draw(pq, '.');
        
        while (true) {   

            while (!pq.isEmpty()) {
                Pos cur_pos = pq.poll();

                int new_row = cur_pos.row + 1;
                int new_col = cur_pos.col;
                
                if (new_row == R || graph[new_row][new_col] == 'x') {             
                    flg = true;
                    break;
                }

                progressQueue.add(new Pos(new_row, new_col));
            }

            if (flg) {
                draw(backupQueue, 'x');
                break;
            } else {
                backupQueue.clear();
                for (Pos pos : progressQueue) {
                    backupQueue.add(new Pos(pos.row, pos.col));
                    pq.add(new Pos(pos.row, pos.col));
                }
                progressQueue.clear();
            }
            
        }
    }

    static void draw(PriorityQueue<Pos> pq, char c) {
        for (Pos pos : pq) {
            graph[pos.row][pos.col] = c;
        }
    }
    
    static PriorityQueue<Pos> bfs (boolean[][] visited, int row, int col) {
        boolean isSeperate = true; 
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        Deque<Pos> deque = new ArrayDeque<>();

        Pos pos = new Pos(row, col);
        deque.add(pos);
        pq.add(pos);
        visited[row][col] = true;
        
        while (!deque.isEmpty()) {
            Pos cur_pos = deque.pollFirst();
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_pos.row + dy[i];
                int new_col = cur_pos.col + dx[i];

                if (new_row < 0 || new_row == R || new_col < 0 || new_col == C) {
                    if (new_row == R) isSeperate = false;
                    continue;
                }
                if (visited[new_row][new_col] || graph[new_row][new_col] == '.') continue;

                Pos new_pos = new Pos(new_row, new_col);
                deque.add(new_pos);
                pq.add(new_pos);
                visited[new_row][new_col] = true;
            }
        }

        if (isSeperate) return pq;
        return null;
    }
    
    static void removeLeftToRight(int height) {
        int cur_row = R - height;
        
        for (int col = 0; col < C; col++) {
            if (graph[cur_row][col] == 'x') {
                graph[cur_row][col] = '.';
                break;
            }
        }
    }

    static void removeRightToLeft(int height) {
        int cur_row = R - height;

        for (int col = C - 1; col >= 0; col--) {
            if (graph[cur_row][col] == 'x') {
                graph[cur_row][col] = '.';
                break;
            }
        }
    }
    
    static void display() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                sb.append(graph[row][col]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}