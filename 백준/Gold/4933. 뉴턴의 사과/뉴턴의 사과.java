import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int T;
    static StringBuilder sb1;
    static StringBuilder sb2;
    static class Node {
        String value;
        Node left;
        Node right;
        Node parent;

        Node(String value) {
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            Stack<String> stackA = new Stack<>();
            Stack<String> stackB = new Stack<>();

            st = new StringTokenizer(br.readLine());
            while (true) {
                String str = st.nextToken();

                if (str.equals("end")) break;
                stackA.push(str);
            }

            st = new StringTokenizer(br.readLine());
            while (true) {
                String str = st.nextToken();

                if (str.equals("end")) break;
                stackB.push(str);
            }

            Node rootA = makeTree(stackA);
            Node rootB = makeTree(stackB);

            sb1 = new StringBuilder();
            sb2 = new StringBuilder();

            dfs(rootA, sb1);
            dfs(rootB, sb2);

            if (sb1.toString().equals(sb2.toString())) sb.append("true").append("\n");
            else sb.append("false").append("\n");

        }

        System.out.println(sb.toString());
    }

    static void dfs(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        if (node.value.equals("nil")) return;

        char c = node.value.charAt(0);
        sb.append(c);

        char left = node.left.value.charAt(0);
        char right = node.right.value.charAt(0);

        if (left >= right) {
            dfs(node.left, sb);
            dfs(node.right, sb);
        } else {
            dfs(node.right, sb);
            dfs(node.left, sb);
        }
    }
    
    static void postorder(Node node) {
        if (node == null) return;

        postorder(node.left);
        postorder(node.right);

        System.out.println(node.value);
    }
    
    static Node makeTree(Stack<String> stack) {
        Node pointer = null;
        Node root = null;
        
        while (!stack.isEmpty()) {
                String str = stack.pop();

                if (root == null) {
                    root = new Node(str);
                    pointer = root;
                    continue;
                }

                if (pointer.right == null) {
                    pointer.right = new Node(str);
                    pointer.right.parent = pointer;

                    if (!str.equals("nil")) {
                        pointer = pointer.right;
                    }
                }else {
                    pointer.left = new Node(str);
                    pointer.left.parent = pointer;

                    if (!str.equals("nil")) {
                        pointer = pointer.left;
                    }
                }

                if (pointer.left != null && pointer.right != null) {
                    Node cur_pointer = pointer;
                    
                    while (true) {
                        if (cur_pointer.left == null || 
                            cur_pointer.right == null || 
                            cur_pointer == root) break;
                        
                        cur_pointer = cur_pointer.parent;
                    }

                    pointer = cur_pointer;
                }
                
            }
        return root;
    }
}