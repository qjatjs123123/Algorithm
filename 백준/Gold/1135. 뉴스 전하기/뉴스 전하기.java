import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

    static class Node{
        int no;
        ArrayList<Node> childList = new ArrayList<>();

        Node(int no) {
            this.no = no;
        }
    }
    
    static int N;
    static Node[] nodeArr;
    static int answer = 0;
    static int[] dp;
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        N = fs.nextInt();
        nodeArr = new Node[N];
        dp = new int[N];
        
        for (int i = 0; i < N; i++) {
            nodeArr[i] = new Node(i);
            dp[i] = -1;
        }
        
        for (int i = 0; i < N; i++) {
            int parent = fs.nextInt();

            if (parent != -1) {
                nodeArr[parent].childList.add(nodeArr[i]);
            }
        }

        System.out.println(dfs(0));
    }

    static int dfs(int cur_no) {
        Node cur_node = nodeArr[cur_no];

        if (dp[cur_no] != -1) return dp[cur_no];
        if (cur_node.childList.size() == 0) {
            return 0;
        }

        int result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Node new_node : cur_node.childList) {
            pq.add( dfs(new_node.no) * -1 );
        }

        int depth = 1;
        while (!pq.isEmpty()) {
            int cur_max = pq.poll() * -1;

            result = Math.max(result, cur_max + depth);
            depth++;
        }

        dp[cur_no] = result;
        return result;
    }
}