import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, T;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][M];
        visited = new boolean[N + 1][M];
        
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) graph[row][col] = Integer.parseInt(st.nextToken());
        }

        
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            controller(x, d, k);
            
            remove();
            
            
        }
        
        StringBuilder sb = new StringBuilder();


        // System.out.println(sb.toString());

        
        System.out.println(calculate());
    }

    static int calculate() {
        int total = 0;

        for (int row = 1; row <= N; row++) {
            for (int col = 0; col < M; col++) {
                total += graph[row][col];
            }
        }

        return total;
    }
    
    static void controller(int x, int d, int k) {

        k = k % M;
        for (int i = x; i <= N; i += x) {
            if (d == 0) rightRotate(i, k);
            else leftRotate(i, k);
        }
    }

    static void remove() {
        boolean flg = false;
        for (int row = 1; row <= N; row++) {
            for (int col = 0; col < M; col++) {
                if (visited[row][col]) continue;

                Deque<int[]> deque = new LinkedList<>();
                Stack<int[]> stack = new Stack<>();

                deque.add(new int[] {row, col});
                stack.add(new int[] {row, col});
                visited[row][col] = true;
                
                while (!deque.isEmpty()) {
                    int[] arr = deque.pollFirst();

                    for (int i = 0; i < 4; i++) {
                        int new_row = arr[0] + dy[i];
                        int new_col = arr[1] + dx[i];

                        if(new_row < 0) new_row = N;
                        else if(new_row == N + 1) new_row = 0;
                        else if(new_col < 0) new_col = M - 1;
                        else if (new_col == M) new_col = 0;
                        
                        if (visited[new_row][new_col]) continue;
                        if (graph[new_row][new_col] != graph[row][col]) continue;

                        deque.add(new int[] {new_row, new_col});
                        stack.add(new int[] {new_row, new_col});
                        visited[new_row][new_col] = true;
                    }
                }

                if (stack.size() == 1) {
                    visited[row][col] = false;
                    
                } else {
                    while (!stack.isEmpty()) {
                        int[] arr = stack.pop();

                        graph[arr[0]][arr[1]] = 0;
                    }
                    flg = true;
                }
            }
        }

        if (!flg) rest();
    }

    static void rest() {
        int total = 0;
        int cnt = 0;
        
        for (int row = 1; row <= N; row++) {
            for (int col = 0; col < M; col++) {
                total += graph[row][col];

                if (graph[row][col] != 0) cnt++;
            }
        }

        double avg = (double)total / cnt;
        
        
        
        for (int row = 1; row <= N; row++) {
            for (int col = 0; col < M; col++) {

                if (graph[row][col] == 0) continue;

                double tmp = graph[row][col];
                if (tmp > avg) graph[row][col]--;
                if (tmp < avg) graph[row][col]++;
            }
        }
    }
    
    static void leftRotate(int idx, int k) {
        int[] arr = graph[idx];
        boolean[] arr_v = visited[idx];
        
        for (int i = 0; i < k; i++) {
            int tmp = arr[0];
            boolean tmp_v = arr_v[0];
                
            for (int j = 1; j < M; j++) {
                arr[j - 1] = arr[j];
                arr_v[j - 1] = arr_v[j];
            }

            arr_v[M - 1] = tmp_v;
            arr[M - 1] = tmp;
        }
    }
    
    static void rightRotate(int idx, int k) {
        int[] arr = graph[idx];
        boolean[] arr_v = visited[idx];
        
        for (int i = 0; i < k; i++) {
            int tmp = arr[M - 1];
            boolean tmp_v = arr_v[M - 1];
            
            for (int j = M - 2; j >= 0; j--) {
                arr[j + 1] = arr[j];
                arr_v[j + 1] = arr_v[j];
            }

            arr_v[0] = tmp_v;
            arr[0] = tmp;
        }
        
    }
    
    static void display(int[][] graph) {
        StringBuilder sb = new StringBuilder();

        for (int row = 1; row < graph.length; row++) {
            for (int col = 0; col < graph[0].length; col++) sb.append(graph[row][col]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}