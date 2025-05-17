import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[] parents;
    static Node[] nodeArr;
    static HashMap<Integer, ArrayList<Integer>> childMap = new HashMap<>();
    static int answer = 0;
    static class Node {
        int value;
        ArrayList<Node> children = new ArrayList<>();

        Node(int value) {
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        parents = new int[N];
        nodeArr = new Node[N];
        Node root = null;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
            nodeArr[i] = new Node(i);

            if (parents[i] == -1) root = nodeArr[i];
            
            if (!childMap.containsKey(parents[i])) {
                childMap.put(parents[i], new ArrayList<>());
                childMap.get(parents[i]).add(i);
            } else {
                childMap.get(parents[i]).add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for (int key : childMap.keySet()) {
            for (int child : childMap.get(key)) {
                if (key != -1) {
                    
                    nodeArr[key].children.add(nodeArr[child]);
                }
            }
        } 

        dfs(root);
        System.out.println(answer);
    }

    static void dfs(Node node) {   
        boolean flg = false;
        if (node.value == K) return;
        for (Node n : node.children) {
            if (n.value == K) continue;
            flg = true;
            dfs(n);
        }

        if (!flg) {
            answer++;
        }
    }
}