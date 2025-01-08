import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static HashMap<Integer, Node> pos = new HashMap<>();
    static int ans = 0;
    static class Node {
        int value = 1;
        ArrayList<Node> childList = new ArrayList<>();
        int node_num;
        Node(int num) {this.node_num = num;}
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

 

        for (int i = 1; i <= 500_000; i++) pos.put(i, new Node(i));
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            Node num1_node = pos.get(num1);
            Node num2_node = pos.get(num2);
            
            num1_node.childList.add(num2_node);
            num2_node.childList.add(num1_node);

            
        }

       
        dfs(pos.get(1), -1);
        if (ans % 2 != 0) System.out.println("Yes");
        else System.out.println("No");



    }

    static int dfs(Node cur_node, int parent) {

        if (cur_node.childList.size() == 1 && cur_node.node_num != 1) {
            return 1;
        }
        ArrayList<Node> new_list = cur_node.childList;
        int total = 0;
        
        for (Node new_node : new_list) {
            if (new_node.node_num == parent) continue;
            total += dfs(new_node, cur_node.node_num);
        }

        ans += total;
        return total;
        
    }
}