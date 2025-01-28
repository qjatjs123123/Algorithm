import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n;
    static ArrayList<int[]>[] graph;
    static int ans = 0;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        
        for (int i = 0; i < n + 1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[parent].add(new int[] {child, weight});
            graph[child].add(new int[] {parent, weight});
        }

        for (int cur_node = 1; cur_node <= n; cur_node++) {
            visited = new boolean[n + 1];
            visited[cur_node] = true;
            recursion(cur_node, 0);
        }

        System.out.println(ans);
    }

    static void recursion(int cur_node, int total) {

         boolean flg = false;
        
         for (int[] cur_arr : graph[cur_node]) {
             if (visited[cur_arr[0]]) continue;

             visited[cur_arr[0]] = true;
             recursion(cur_arr[0], total + cur_arr[1]);
             flg = true;
         }       

        if (!flg) ans = Math.max(ans, total);
    }
}