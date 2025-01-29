import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static ArrayList<Integer>[] graph;
    static int N;
    static Deque<Integer> rootA_deque;
    static Deque<Integer> rootB_deque;
    static boolean[] visited;
    static boolean flg = false;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            graph = new ArrayList[N + 1];
            int[] rootCnt = new int[N + 1];
            rootA_deque = new LinkedList<>();
            rootB_deque = new LinkedList<>();
            
            for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
            
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                graph[a].add(b);
                rootCnt[b]++;
            }
            int root = findRoot(rootCnt);
            
            st = new StringTokenizer(br.readLine());
            int rootA = Integer.parseInt(st.nextToken());
            int rootB = Integer.parseInt(st.nextToken());

            flg = false;
            visited = new boolean[N + 1];
            visited[root] = true;
            rootA_deque.add(root);
            dfs(root, rootA, rootA_deque);

            flg = false;
            visited = new boolean[N + 1];
            visited[root] = true;
            rootB_deque.add(root);
            dfs(root, rootB, rootB_deque);
            
            System.out.println(findCommonRoot());
        }
    }

    static int findCommonRoot() {
        int ans = 0;
        while (!rootA_deque.isEmpty() && !rootB_deque.isEmpty()) {
            int rootA = rootA_deque.pollFirst();
            int rootB = rootB_deque.pollFirst();

            if (rootA != rootB) break;
            ans = rootA;
        }

        return ans;
    }
    
    static int findRoot(int[] rootCnt) {
        int root = 0;
        for (int i = 1; i <= N; i++) {
            if (rootCnt[i] == 0) {
                root = i;
                break;
            }
        }
        return root;
    }
    
    static void dfs(int cur_node, int end_node, Deque<Integer> deque) {
        if (cur_node == end_node) {
            flg = true;
            return;
        }
        
        for (int new_node : graph[cur_node]) {
            if (visited[new_node] || flg) continue;
            
            visited[new_node] = true;
            deque.add(new_node);
    
            dfs(new_node, end_node, deque);
            if (flg) continue; 
            deque.pollLast();
        }
    }
}