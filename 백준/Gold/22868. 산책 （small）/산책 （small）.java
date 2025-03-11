import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int S, E;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();

        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[] track = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[] {0, S});
        visited[S] = true;
        track[S] = -1;
        int ans = 0;
        boolean[] visited1 = new boolean[N + 1];
        
        while(!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_dist = cur_arr[0];
            int cur_node = cur_arr[1];

            if (cur_node == E) {

                int cur_track = track[E];
                ans += cur_dist;
                while (true) {
                    if (cur_track == -1) break;

                    visited1[cur_track] = true;
                    cur_track = track[cur_track];
                }
                
                break;
            }
            
            ArrayList<Integer> new_graph = graph[cur_node];
            Collections.sort(new_graph);
            
            for (int new_node : new_graph) {
                if (visited[new_node]) continue;

                visited[new_node] = true;
                track[new_node] = cur_node;
                deque.add(new int[] {cur_dist + 1, new_node});
            }
        }

        visited1[S] = false;
        visited1[E] = false;
        
        deque = new LinkedList<>();
        deque.add(new int[] {0, E});
        visited1[E] = true;

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_dist = cur_arr[0];
            int cur_node = cur_arr[1];

            if (cur_node == S) {
                ans += cur_dist;
                break;
            }

            ArrayList<Integer> new_graph = graph[cur_node];
            Collections.sort(new_graph);
            
            for (int new_node : new_graph) {
                if (visited1[new_node]) continue;

                visited1[new_node] = true;
                deque.add(new int[] {cur_dist + 1, new_node});
            }
        }

        System.out.println(ans);
    }

}