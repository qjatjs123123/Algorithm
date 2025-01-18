import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, X;
    static int[] arr;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> re_graph = new HashMap<>();
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        
        for (int i = 1; i <= N; i++) {
            ArrayList<Integer> deque = new ArrayList<>();
            ArrayList<Integer> deque1 = new ArrayList<>();
            graph.put(i, deque);
            re_graph.put(i, deque1);
        }
        
        ArrayList<Integer> parentList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            ArrayList<Integer> list = graph.get(from);
            list.add(to);
            list = re_graph.get(to);
            list.add(from);

            if (to == X) parentList.add(from);
        }    

        int childCnt = dfs(X, graph) - 1;       
        int parentCnt = bfs(parentList);

        int min_num = parentCnt + 1;
        int max_num = N - childCnt;

        
        System.out.println(min_num + " " + max_num);
    }

    static int bfs(ArrayList<Integer> parentList) {
        visited = new boolean[N + 1];

        Deque<Integer> deque = new LinkedList<>();
        for(int node : parentList) {
            visited[node] = true;
            deque.add(node);
        }
        int ans = parentList.size();

        while (!deque.isEmpty()) {
            int cur_node = deque.pollFirst();

            ArrayList<Integer> new_list = re_graph.get(cur_node);
            for (int new_node : new_list) {
                if (visited[new_node]) continue;
                ans++;
                visited[new_node] = true;
                deque.add(new_node);
            }
        }

        return ans;
    }
    
    static int dfs(int node, HashMap<Integer, ArrayList<Integer>> graph) {

        ArrayList<Integer> new_list = graph.get(node);
        
        int total = 1;
        
        for (int new_node : new_list) {
            if (visited[new_node]) continue;
            visited[new_node] = true;
            total += dfs(new_node, graph);
        }

        return total;
    }
}