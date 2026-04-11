import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] graph;
    static int N, M;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
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
        N = fs.nextInt(); M = fs.nextInt();
        graph = new int[N][M];
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                graph[row][col] = fs.nextInt();
                if (graph[row][col] == 0) list.add(new int[] {row, col});
            }
        }

        int answer = 0;
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int rowA = list.get(i)[0];
                int colA = list.get(i)[1];
                int rowB = list.get(j)[0];
                int colB = list.get(j)[1];

                graph[rowA][colA] = 1;
                graph[rowB][colB] = 1;
                answer = Math.max(answer, bfs());
                graph[rowA][colA] = 0;
                graph[rowB][colB] = 0;
            }
        }

        System.out.println(answer);
    }

    static int bfs() {
        int result = 0;
        boolean[][] visited = new boolean[N][M];
        Deque<int[]> deque = new ArrayDeque<>();
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (graph[row][col] != 2) continue;
                if (visited[row][col]) continue;

                deque.add(new int[] {row, col});
                visited[row][col] = true;
                int tmp = 1;
                boolean isDead = true;

                while (!deque.isEmpty()) {
                    int[] cur_arr = deque.pollFirst();

                    for (int i = 0; i < 4; i++) {
                        int new_row = cur_arr[0] + dy[i];
                        int new_col = cur_arr[1] + dx[i];

                        if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M) continue;
                        if (visited[new_row][new_col]) continue;
                        if (graph[new_row][new_col] == 0) {
                            isDead = false;
                            continue;
                        } else if (graph[new_row][new_col] == 1) {
                            continue;
                        } else {
                            deque.add(new int[] {new_row, new_col});
                            visited[new_row][new_col] = true;
                            tmp++;
                        }
                    }
                }
                
                if (isDead) result += tmp;
            }
        }

        return result;
    }
}