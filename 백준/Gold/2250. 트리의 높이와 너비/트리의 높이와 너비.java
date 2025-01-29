import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static boolean isNotRoot[];
    static Node NodeArr[];
    static Deque<Integer>[] depthArr;
    static Deque<Integer> deque = new LinkedList<>();
    
    static class Node {
        int node, left, right;

        Node(int node, int left, int right){
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        isNotRoot = new boolean[N + 1];
        NodeArr = new Node[N + 1];
        depthArr = new Deque[N + 1];

        for (int i = 0; i < N + 1; i++) depthArr[i] = new LinkedList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (left != -1) isNotRoot[left] = true;
            if (right != -1) isNotRoot[right] = true;

            NodeArr[node] = new Node(node, left, right);
        }

        int rootNum = getRootNum();
        inOrder(rootNum, 0);

        getAnswer();

        
    }

    static void getAnswer() {
        HashMap<Integer, Integer> dict = new HashMap<>();

        int idx = 0;
        for (int node : deque) {
            dict.put(node, idx);
            idx++;
        }

        int depth = 1;
        int width = 1;
        
        for (int i = 0; i <= N; i++) {
            Deque<Integer> cur_deque = depthArr[i];
            
            if(depthArr[i].isEmpty()) break;

            if (cur_deque.size() == 1) continue;

            int left_idx = dict.get(cur_deque.pollFirst());
            int right_idx = dict.get(cur_deque.pollLast());
            int new_width = right_idx - left_idx + 1;

            if (new_width > width){
                depth = i + 1;
                width = new_width;
            }
            
        }

        System.out.println(depth + " " + width);
    }
    
    static void inOrder(int nodeNum, int cur_depth) {
        Node curNode = NodeArr[nodeNum];

        if (curNode.left == -1 && curNode.right == -1) {
            depthArr[cur_depth].add(nodeNum);
            deque.add(nodeNum);
            return;
        }

        if (curNode.left != -1) inOrder(curNode.left, cur_depth + 1);
        depthArr[cur_depth].add(nodeNum);
        deque.add(nodeNum);
        if (curNode.right != -1) inOrder(curNode.right, cur_depth + 1);
    }
    
    static int getRootNum() {
        int rootNum = 0;
        
        for (int i = 1; i < N + 1; i++) {
            if (!isNotRoot[i]) {
                rootNum = i;
                break;
            }
        }

        return rootNum;
    }
}