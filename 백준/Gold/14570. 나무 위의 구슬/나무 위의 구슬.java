import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static class Node {
        int value;
        int cnt = 0;
        Node[] child = new Node[2];

        Node(int value) {
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Node[] nodeArr = new Node[N + 1];

        for (int i = 0; i <= N; i++) nodeArr[i] = new Node(i);
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (u != -1) {
                nodeArr[i].child[0] = nodeArr[u];
                nodeArr[i].cnt++;
            }

            if (v != -1) {
                nodeArr[i].child[1] = nodeArr[v];
                nodeArr[i].cnt++;
            }
        }

        st = new StringTokenizer(br.readLine());
        long K = Long.parseLong(st.nextToken());

        dfs(nodeArr[1], K);
    }

    static void dfs(Node cur_node, long k) {
        if (cur_node.cnt == 0) {
            System.out.println(cur_node.value);
            return;
        }
        else if (cur_node.cnt == 1) {
            if (cur_node.child[0] != null) dfs(cur_node.child[0], k);
            else dfs(cur_node.child[1], k);
        } else {
            long new_k = k / 2L;
            
            if (k % 2 != 0) {
                dfs(cur_node.child[0], new_k + 1);
            } else {
                dfs(cur_node.child[1], new_k);
            }
        }
    }
}