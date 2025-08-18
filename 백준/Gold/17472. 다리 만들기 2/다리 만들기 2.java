import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph, dp;
    static boolean[][] visited;
    static ArrayList<int[]>[] posList;
    static int end = 0;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int[] parents;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        posList = new ArrayList[7];
        parents = new int[7];
        
        for (int i = 1; i <= 6; i++) {
            posList[i] = new ArrayList<>();
            parents[i] = i;
        }
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; col++) graph[row][col] = Integer.parseInt(st.nextToken());
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (graph[row][col] == 0 || visited[row][col]) continue;

                end++;
                bfs(row, col);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int start = 1; start <= 6; start++) {
            ArrayList<int[]> list = posList[start];

            for (int[] arr : list) {
                int cur_row = arr[0];
                int cur_col = arr[1];

                for (int direct = 0; direct < 4; direct++) {
                    cur_row += dy[direct];
                    cur_col += dx[direct];
                    boolean flg = false;
                    
                    while (true) {
                        if (cur_row < 0 || cur_row == N || cur_col < 0 || cur_col == M) break;
                        if (graph[cur_row][cur_col] == 1) {
                            flg = true;
                            break;
                        }

                        cur_row += dy[direct];
                        cur_col += dx[direct];
                    }

                    if (flg && ( dp[ arr[0] ][ arr[1] ] != dp[ cur_row ][ cur_col ] )) {
                        int dist = Math.abs(cur_row - arr[0]) + Math.abs(cur_col - arr[1]) - 1;

                        if (dist >= 2) pq.add(new int[] {dist, dp[ arr[0] ][ arr[1] ], dp[ cur_row ][ cur_col ]});
                    }
                }
            }   
        }

        int answer = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();

            if (!union(arr[1], arr[2])) continue;
            
            answer += arr[0];
            cnt++;
        }

        if (cnt == end - 1) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
        
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootA] = parents[rootB];
        return true;
    }
    
    static int find (int node) {
        if (parents[node] == node) return node;

        return parents[node] = find(parents[node]);
    }
    
    static void display() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) sb.append(dp[row][col]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    
    static void bfs(int row, int col) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {row ,col});
        visited[row][col] = true;
        dp[row][col] = end;
        posList[end].add(new int[] {row, col});

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

                posList[end].add(new int[] {new_row, new_col});
                dp[new_row][new_col] = end;
                deque.add(new int[] {new_row, new_col});
                visited[new_row][new_col] = true;
            }
        }
    }
}