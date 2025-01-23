import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    
    static HashMap<Integer, Boolean>[][] graph;
    static HashMap<Integer, Boolean>[][] visited_graph;
    static int RES = 500;
    static boolean[] visited;
    static HashMap<Integer, int[]> dict = new HashMap();
    static int[] tutlePos = new int[] {0,0};
    static int[] dx = new int[] {0, 0, 1, -1};
    static int[] dy = new int[] {1, -1, 0, 0};
    static int COUNT = 0;
    static Deque<int[]> deque = new LinkedList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new HashMap[1001][1001];
        visited = new boolean[N];
        visited_graph = new HashMap[1001][1001];
        
        
        for (int row = 0; row <= 1000; row++) {
            for (int col = 0; col <= 1000; col++) {
                HashMap<Integer, Boolean> hash = new HashMap<>();
                HashMap<Integer, Boolean> v = new HashMap<>();
                
                visited_graph[row][col] = v;
                graph[row][col] = hash;
            }
        }
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + RES;
            int y1 = Integer.parseInt(st.nextToken()) + RES;
            int x2 = Integer.parseInt(st.nextToken()) + RES;
            int y2 = Integer.parseInt(st.nextToken()) + RES;
            
            dict.put(row, new int[] {x1, y1});
            
            draw(x1, y1, x2, y2, row);
        }
        init();

        int ans = 0;
        
        
        
        while (true) {
            bfs();
           
            if (COUNT == N) break;
            
            up();  
            
            ans++;
            
        }
        System.out.println(ans);
    }

    static void up() {
        for (int i = 0 ; i < N; i++) {
            if (!visited[i]) {
                int[] arr = dict.get(i);
                visited[i] = true;
                deque.add(new int[] {arr[0], arr[1], i});
                COUNT++;
                break;
            }
        }
    }
    
    static void init() {
        HashMap<Integer, Boolean> hash = graph[500][500];
        HashMap<Integer, Boolean> v = visited_graph[500][500];
        
        for (int key : hash.keySet()) {
            if (visited[key]) continue;
            deque.add(new int[] {500, 500, key});
            visited[key] = true;
            v.put(key, true);
            COUNT++;
        }
    }
    
    static void bfs() {

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            HashMap<Integer, Boolean> hash1 = graph[cur_arr[0]][cur_arr[1]];

            for (int i = 0; i < 4; i++) {
                int new_row = cur_arr[0] + dy[i];
                int new_col = cur_arr[1] + dx[i];
                
                if (new_row < 0 || new_row == 1001 || new_col < 0 || new_col == 1001) continue;
                HashMap<Integer, Boolean> v = visited_graph[new_row][new_col];
                HashMap<Integer, Boolean> hash = graph[new_row][new_col];
                
                
                if (!hash.containsKey(cur_arr[2])) continue;
                if (v.containsKey(cur_arr[2])) continue;

                v.put(cur_arr[2], true);
                deque.add(new int[] {new_row, new_col, cur_arr[2]});

                
                for (int key : hash.keySet()) {
                    if (visited[key]) continue;
                    deque.add(new int[] {new_row, new_col, key});
                    visited[key] = true;
                    v.put(key, true);
                    COUNT++;
                }
                
            }
        }
    }
    
    static void draw(int x1, int y1, int x2, int y2, int num) {
        for (int x = x1; x <= x2; x++) {
            HashMap<Integer, Boolean> cur_hash = graph[x][y1];
            cur_hash.put(num, true);

            cur_hash = graph[x][y2];
            cur_hash.put(num, true);
        }

        for (int y = y1; y <= y2; y++) {
            HashMap<Integer, Boolean> cur_hash = graph[x1][y];
            cur_hash.put(num, true);

            cur_hash = graph[x2][y];
            cur_hash.put(num, true);
        }
    }
}