import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, R;
    static ArrayList<int[]>[] graph;
    static boolean[] visited;
    static int root_length = 0;
    static int leaf_length = 0;
    static boolean isRoot = true;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a  = Integer.parseInt(st.nextToken());
            int b  = Integer.parseInt(st.nextToken());
            int d  = Integer.parseInt(st.nextToken());

            graph[a].add(new int[] {b, d});
            graph[b].add(new int[] {a, d});

            
        }
        visited[R] = true;
        recursion(R, 0);

        if (isRoot) root_length = leaf_length;
        
        
        System.out.println(root_length + " " + (leaf_length - root_length));
    }

    static void recursion(int cur_node, int cur_length) {

        boolean flg = false;

        
        if (isRoot) {
            int cnt = 0;
            for (int[] new_arr : graph[cur_node]) {
                int new_node = new_arr[0];
                if (visited[new_node]) continue;

                cnt++;
            }

            if (cnt >= 2) {
                isRoot = false;
                root_length = cur_length;
            }
        }
        
        
        for (int[] new_arr : graph[cur_node]) {
            int new_node = new_arr[0];
            if (visited[new_node]) continue;

            visited[new_node] = true;
            flg = true;
            recursion(new_node, cur_length + new_arr[1]);
        }

        if (!flg) leaf_length = Math.max(leaf_length, cur_length);
    }
}