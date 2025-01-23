import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int R, C;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dx = new int[] {0, 0, 1, -1};
    static int[] dy = new int[] {1, -1, 0, 0};
    static Deque<Pos> waterQueue = new LinkedList<>();
    static Deque<Pos> dociQueue = new LinkedList<>();
    static boolean ans;
    
    static class Pos{
        int row, col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        visited = new boolean[R][C];
        
        for (int row = 0; row < R; row++) {
            st = new StringTokenizer(br.readLine());
            String rowData = st.nextToken();
            
            for (int col = 0; col < C; col++) {
                graph[row][col] = rowData.charAt(col);

                if (graph[row][col] == '*'){
                    waterQueue.add(new Pos(row, col));
                    visited[row][col] = true;
                } 
                if (graph[row][col] == 'S') {
                    dociQueue.add(new Pos(row, col));
                    visited[row][col] = true;
                }
            }
        }

        int time = 1;

        while(true) {
            bfs(waterQueue, false);
            bfs(dociQueue, true);

            if (ans) {
                System.out.println(time);
                break;
            }
            if (dociQueue.isEmpty()) {
                System.out.println("KAKTUS");
                break;
            }
            time++;
        }
        
    }

    static void bfs(Deque<Pos> queue, boolean isDoci) {
        Deque<Pos> new_queue = new LinkedList<>();
        
        while(!queue.isEmpty()) {
            Pos cur_pos = queue.pollFirst();
    
            
            
            for (int i = 0; i < 4; i++) {
                int new_row = cur_pos.row + dy[i];
                int new_col = cur_pos.col + dx[i];
  
                
                if (new_row < 0 || new_row == R || new_col < 0 || new_col == C) continue;

                if (isDoci && graph[new_row][new_col] == 'D') {
                    ans = true;
                    return;
                }
                if (!isDoci && graph[new_row][new_col] == 'D') continue;
                
                if (visited[new_row][new_col]) continue;
                if (graph[new_row][new_col] == 'X') continue;

                visited[new_row][new_col] = true;
                new_queue.add(new Pos(new_row, new_col));
                
            }
        }     
        if (isDoci) {
            dociQueue = new_queue;
        } else{
            waterQueue = new_queue;
        }

    }
}