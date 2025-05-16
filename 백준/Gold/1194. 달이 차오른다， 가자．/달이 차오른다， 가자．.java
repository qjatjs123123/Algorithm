import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static char[][] graph;
    static boolean[][][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    
    static class Pos {
        int row, col, bitmask, dist;

        Pos(int row, int col, int bitmask, int dist) {
            this.row = row;
            this.col = col;
            this.bitmask = bitmask;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        visited = new boolean[64][N][M];
        
        Deque<Pos> deque = new ArrayDeque<>();
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < M; col++) {
                graph[row][col] = str.charAt(col);

                if (graph[row][col] == '0') {
                    deque.add(new Pos(row, col, 0, 0));
                    visited[0][row][col] = true; 
                }
            }
        }
        boolean flg = false;
        while (!deque.isEmpty()) {
            Pos pos = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                int new_row = pos.row + dy[i];
                int new_col = pos.col + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                if (graph[new_row][new_col] == '#') continue;
                if (visited[pos.bitmask][new_row][new_col]) continue;

                if (graph[new_row][new_col] == '1') {
                    System.out.println(pos.dist + 1);
                    flg = true;
                    return;
                } else if (graph[new_row][new_col] == '.' || graph[new_row][new_col] == '0') {
                    deque.add(new Pos(new_row, new_col, pos.bitmask, pos.dist + 1));
                    visited[pos.bitmask][new_row][new_col] = true;
                } else {
                    char c = graph[new_row][new_col];
                    if (Character.isUpperCase(c)) {
                        if (!keyCheck(pos.bitmask, graph[new_row][new_col])) continue;

                        deque.add(new Pos(new_row, new_col, pos.bitmask, pos.dist + 1));
                        visited[pos.bitmask][new_row][new_col] = true;
                    } else {
                        int new_bit = pos.bitmask | (1 << getIndex(graph[new_row][new_col]));
                        deque.add(new Pos(new_row, new_col, new_bit, pos.dist + 1));
                        visited[new_bit][new_row][new_col] = true;
                    }
                    
                }
                
                
            }
        }

        if (!flg) System.out.println(-1);
    }

    static boolean keyCheck (int bitmask, char c) {
        int upperbit = 1 << getIndex(c);

        return (bitmask & upperbit) > 0;
    }
    
    static int getIndex(char c) {
        if (c == 'f' || c == 'F') return 0;
        else if (c == 'e' || c =='E') return 1;
        else if (c == 'd' || c == 'D') return 2;
        else if (c == 'c' || c == 'C') return 3;
        else if (c == 'b' || c == 'B') return 4;
        else if (c == 'a' || c == 'A') return 5;
        return -1;
    }
}