import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
// class Node => ArrayList<Node> childList, value, no
// NodeArr[n + 1]
// n, m => input
// 
// for(i => 1 ~ n) 
// Node node = new Node();
// i == 1 ?  NodeArr[1] = node;
// else ? NodeArr[input].childList.add(node);

// for (i => 0 ~ m)
// i, m => input
// nodeArr[i].value += main

// dfs(Node cur_node, int cur_score) {}
// answer[cur_node.no] = cur_score
// for (Node node : cur_node.childList) dfs(new_node, cur_score + cur_node.value)



class Main {
    static Node[] nodeArr;
    static int[] answer;
    static class Node {
        int value, no;
        ArrayList<Node> childList = new ArrayList<>();

        Node (int no) {
            this.no = no;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, m;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nodeArr = new Node[n + 1];
        answer = new int[n + 1];
        for (int i = 1; i <= n; i++) nodeArr[i] = new Node(i);
        
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (i == 1) {
                continue;
            }

            nodeArr[parent].childList.add(nodeArr[i]);            
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            
            nodeArr[no].value += score;
        }

        dfs(nodeArr[1], nodeArr[1].value);

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) sb.append(answer[i]).append(" ");

        System.out.println(sb.toString());
    }

    static void dfs(Node cur_node, int cur_score) {
        answer[cur_node.no] = cur_score;
        
        for (Node new_node : cur_node.childList) {
            dfs(new_node, cur_score + new_node.value);
        }
    }
}