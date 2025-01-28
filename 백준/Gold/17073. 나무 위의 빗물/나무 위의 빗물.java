import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, W;
    static int cnt = 0;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            graph[U].add(V);
            graph[V].add(U);
        }
        visited[1] = true;
        recursion(1);
        
        System.out.println(String.format("%.6f", (double)W / cnt));

    }

    static void recursion(int node) {

        boolean flg = false;
        
        for (int new_node : graph[node]) {
            if  (visited[new_node]) continue;
            visited[new_node] = true;
            recursion(new_node);
            flg = true;
        }
        
        if (!flg) cnt++;
    }
}