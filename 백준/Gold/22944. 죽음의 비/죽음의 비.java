import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, H, D;
    static char[][] graph;
    static boolean[][][] visited;
    static int startRow, startCol = 0;
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
    static HashMap<Integer, Integer> map = new HashMap<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new char[N][N];
        visited = new boolean[N][N][11];
        int idx = 1;
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < N; col++) {
                graph[row][col] = str.charAt(col);

                if (graph[row][col] == 'S') {
                    startRow = row;
                    startCol = col;
                }
                if (graph[row][col] == 'U') {
                    map.put(row*1000 + col, idx++);
                }
            }
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {0, H, 0, startRow, startCol, 0});
        visited[startRow][startCol][0] = true;

        while (!deque.isEmpty()) {
            int[] arr = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                int new_row = arr[3] + dy[i];
                int new_col = arr[4] + dx[i];

                if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
                if (visited[new_row][new_col][arr[5]]) continue;

                if (graph[new_row][new_col] == 'E') {
                    System.out.println(arr[0] + 1);
                    return;
                }
                if (graph[new_row][new_col] == 'U') {
                    int key = map.get(new_row*1000 + new_col);
                    deque.add(new int[] {arr[0] + 1, arr[1], D - 1, new_row, new_col, key});
                    visited[new_row][new_col][arr[5]] = true;
                    visited[new_row][new_col][key] = true;
                } else {
                    if (arr[2] > 0){ 
                        deque.add (new int[] {arr[0] + 1, arr[1], arr[2] - 1, new_row, new_col, arr[5]});
                        visited[new_row][new_col][arr[5]] = true;
                    }
                    else {
                        if (arr[1] > 1) {
                            deque.add(new int[] {arr[0] + 1, arr[1] - 1, arr[2], new_row, new_col, arr[5]});
                            visited[new_row][new_col][arr[5]] = true;
                        }
                    }
                }
            }
        }

        System.out.println(-1);
        
    }
}