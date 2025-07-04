import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static String bitStr;
    static int badApple_1, badApple_2;
    static ArrayList<Node> nodeList = new ArrayList<>();
    static boolean flg = false;
    static class Node {
        int in, out;
        Node parent;
        ArrayList<Node> child = new ArrayList<>();

        Node() {
            
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        bitStr = st.nextToken();

        st = new StringTokenizer(br.readLine());
        badApple_1 = Integer.parseInt(st.nextToken());
        badApple_2 = Integer.parseInt(st.nextToken());
        
        Node root = new Node();

        makeTree(root, 0);

        Deque<Node> deque1 = new ArrayDeque<>();
        Deque<Node> deque2 = new ArrayDeque<>();

        Node node1 = nodeList.get(0);
        Node node2 = nodeList.get(1);

        Stack<int[]> stack1 = new Stack<>();
        Stack<int[]> stack2 = new Stack<>();
        while (true) {
            if (node1 == root) break;

            stack1.push(new int[] {node1.in, node1.out});
            node1 = node1.parent;
        }

        while (true) {
            if (node2 == root) break;

            stack2.push(new int[] {node2.in, node2.out});
            node2 = node2.parent;
        }

        int[] answer = null;

        while (true) {
            if (stack1.isEmpty() || stack2.isEmpty()) break;
            int[] arr1 = stack1.pop();
            int[] arr2 = stack2.pop();

            if (arr1[0] == arr2[0] && arr1[1] == arr2[1]) answer = arr1;
            else break;
        }

        System.out.println(answer[0] + " " + answer[1]);
    }

    
    
    static void makeTree(Node prev, int depth) {
        if (depth == bitStr.length()) return;
        
        char c = bitStr.charAt(depth);

        if (c == '0') {
            Node node = new Node();
            node.in = depth + 1;
            if (node.in == badApple_1 || node.in == badApple_2) nodeList.add(node);
            prev.child.add(node);
            node.parent = prev;
            makeTree(node, depth + 1);
        } else {
            prev.out = depth + 1;
            if (prev.out == badApple_1 || prev.out == badApple_2) nodeList.add(prev);
            makeTree(prev.parent, depth + 1);
        }
    }
}