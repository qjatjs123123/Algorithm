import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> routes = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> one_routes = new ArrayList<>();
    static HashMap<Integer, ArrayList<Integer>> dict = new HashMap<>();
    static int[][] ans;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ans = new int[n + 1][n + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                ans [i][j] = -1; 
            }
        }

        for (int i = 0; i < n + 1; i++) {
            ArrayList<Integer> new_list = new ArrayList<>();
            routes.add(new_list);
            ArrayList<Integer> new_list_1 = new ArrayList<>();
            one_routes.add(new_list_1);
        }

        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            
            if (b == 1) {
                ArrayList<Integer> routes_list = routes.get(u);
                routes_list.add(v);
                routes_list = routes.get(v);
                routes_list.add(u);
            } else {
                ArrayList<Integer> routes_list = routes.get(u);
                routes_list.add(v);
                ArrayList<Integer> one_routes_list = one_routes.get(v);
                one_routes_list.add(u);
            }         
        }
        for (int i = 1; i < n + 1; i++) {
        	ArrayList<Integer> tmp = getPossibleRoute(i);
        	dict.put(i, tmp);
        	
        }
        
        
        for (int i = 1; i < n + 1; i++)
            bfs(i);

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(ans[s][e]).append("\n");
        }

        System.out.println(sb.toString());
    }

    static ArrayList<Integer> getPossibleRoute(int node) {
        boolean[] visited = new boolean[n + 1];
        ArrayList<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        
        deque.add(node);
        list.add(node);
        visited[node] = true;

        while (!deque.isEmpty()) {
            int cur_node = deque.pollFirst();

            ArrayList<Integer> new_list = routes.get(cur_node);

            for (int new_node : new_list) {
                if (visited[new_node]) continue;
                visited[new_node] = true;
                list.add(new_node);
                deque.add(new_node);
            }
        }
        
        return list;
    }
    
    static void bfs(int start) {
        boolean[] visited = new boolean[n + 1];

        Deque<int[]> deque = new LinkedList<>();
        visited[start] = true;

        deque.add(new int[] {start, 0});

        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_num = cur_arr[0];
            int cur_val = cur_arr[1];
            
            ArrayList<Integer> new_routes = dict.get(cur_num);
            
            for (int new_num : new_routes) {
                if (ans[start][new_num] != -1) continue;
//                if (visited[new_num]) continue;
                ans[start][new_num] = cur_val;
                visited[new_num] = true;
                ArrayList<Integer> one_routes_list = one_routes.get(new_num);
                
                for (int new_routes_num : one_routes_list){
                    if (visited[new_routes_num]) continue;
                    visited[new_routes_num] = true;
                    deque.add(new int[] {new_routes_num, cur_val + 1});
                }
            }
            
        }
    }
}