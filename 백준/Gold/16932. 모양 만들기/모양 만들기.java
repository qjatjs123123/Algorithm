import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph, dp;
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
        N = fs.nextInt();
        M = fs.nextInt();

        graph = new int[N][M];
        dp = new int[N][M];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                graph[row][col] = fs.nextInt();
                dp[row][col] = -1;
            }
        }


        int[] dy = new int[] {1, -1, 0, 0};
        int[] dx = new int[] {0, 0, 1, -1};
        boolean[][] visited = new boolean[N][M];
        int group = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (dp[row][col] != -1) continue;
                if (graph[row][col] == 0) {
                    dp[row][col] = 0;
                    continue;
                }

                Deque<int[]> deque = new ArrayDeque<>();
                Stack<int[]> stack = new Stack<>();
                deque.add(new int[] {row, col});
                stack.push(new int[] {row, col});
                visited[row][col] = true;
                
                while (!deque.isEmpty()) {
                    int[] cur_arr = deque.pollFirst();

                    for (int i = 0; i < 4; i++) {
                        int new_row = cur_arr[0] + dy[i];
                        int new_col = cur_arr[1] + dx[i];

                        if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M) continue;
                        if (visited[new_row][new_col]) continue;
                        if (graph[new_row][new_col] == 0) continue;
                        
                        stack.push(new int[] {new_row, new_col});
                        deque.add(new int[] {new_row, new_col});
                        visited[new_row][new_col] = true;
                    }
                }
                if (stack.isEmpty()) continue;
                
                int cnt = stack.size();
                while (!stack.isEmpty()) {
                    int[] cur_arr = stack.pop();

                    dp[ cur_arr[0] ][ cur_arr[1] ] = group;
                }
                map.put(group, cnt);
                group++;
                
            }
        }   


        
        int answer = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                int total = 0;
                if (graph[row][col] == 0) {
                    HashSet<Integer> set = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int new_row = row + dy[i];
                        int new_col = col + dx[i];
                        
                        if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M) continue;
                        if (dp[new_row][new_col] == 0) continue;
                        set.add(dp[new_row][new_col]);
                    }

                    for (int key : set) total += map.get(key);
                    answer = Math.max(answer, total + 1);
                }

            }   
        }

        System.out.println(answer);
    }
}