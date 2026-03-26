import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
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
        int N = fs.nextInt(); int M = fs.nextInt();
        int[][] graph = new int[N][M];
        int[][] new_graph = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        HashMap<Integer, Integer> countSet = new HashMap<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) graph[row][col] = fs.nextInt();
        }

        int[] dy = new int[] {1, -1, 0, 0};
        int[] dx = new int[] {0, 0, 1, -1};
        int id = 1;
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (visited[row][col] || graph[row][col] == 0) continue;

                Deque<int[]> deque = new ArrayDeque<>();
                deque.add(new int[] {row, col});
                visited[row][col] = true;
                new_graph[row][col] = id;
                int count = 1;

                while (!deque.isEmpty()) {
                    int[] cur_arr = deque.pollFirst();

                    int cur_row = cur_arr[0];
                    int cur_col = cur_arr[1];

                    for (int i = 0; i < 4; i++) {
                        int new_row = cur_row + dy[i];
                        int new_col = cur_col + dx[i];

                        if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                        if (visited[new_row][new_col]) continue;
                        if (graph[new_row][new_col] == 0) continue;

                        new_graph[new_row][new_col] = id;
                        count++;
                        visited[new_row][new_col] = true;
                        deque.add(new int[] {new_row, new_col});
                    }
                }
                countSet.put(id, count);
                id++;
            }
        }

        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (new_graph[row][col] == 0) {

                    int tmp = 1;
                    HashSet<Integer> set = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int new_row = row + dy[i];
                        int new_col = col + dx[i];

                        if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
                        if (new_graph[new_row][new_col] == 0) continue;
                        set.add(new_graph[new_row][new_col]);
                    }

                    for (int n : set) tmp += countSet.get(n);
                    answer = Math.max(answer, tmp);
                }
            }
        }

        
        System.out.println(answer);
    }
}