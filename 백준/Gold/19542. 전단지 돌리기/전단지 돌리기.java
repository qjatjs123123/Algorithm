import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, S, D;
    static Node[] nodeArr;
    static ArrayList<Node>[] graph;
    static Node root;
    static int answer = 0;
    static boolean[] visited;
    static class Node {
        int no;
        ArrayList<Node> childList = new ArrayList<>();
        int maxDepth = 0;
        
        Node(int no) {
            this.no = no;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        nodeArr = new Node[N + 1];
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            nodeArr[i] = new Node(i);
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent_idx = Integer.parseInt(st.nextToken());
            int child_idx = Integer.parseInt(st.nextToken());

            nodeArr[parent_idx].childList.add(nodeArr[child_idx]);
            graph[parent_idx].add(nodeArr[child_idx]);
            graph[child_idx].add(nodeArr[parent_idx]);
        }

        root = nodeArr[S];
        visited[S] = true;
        dfs(root);

        visited = new boolean[N + 1];
        visited[S] = true;
        dfs1(root);

        // for (int i = 1; i <= N; i++) System.out.println(nodeArr[i].maxDepth);
        System.out.println(answer * 2);
    }

    static void dfs1(Node cur_node) {        
        ArrayList<Node> list = graph[cur_node.no];
        
        for (Node new_node : list) {
            if (visited[new_node.no]) continue;
            if (new_node.maxDepth < D) continue;

            answer++;
            visited[new_node.no] = true;
            dfs1(new_node);
        }

        
    }
    
    static int dfs(Node cur_node) {
        
        int depth = 0;
        ArrayList<Node> list = graph[cur_node.no];
        for (Node new_node : list) {
            if (visited[new_node.no]) continue;

            visited[new_node.no] = true;
            depth = Math.max(depth, dfs(new_node) + 1);
        }

        cur_node.maxDepth = depth;
        return depth;
    }
}