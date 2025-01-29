import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n;
    static int[] arr;
    static boolean[] isRoot;
    static Node[] nodeArr;
    static Deque<Integer> depthDeque[];
    static HashMap<Integer, int[]> dict = new HashMap<>();
    
    static class Node {
        int node, left, right;

        Node(int node, int left, int right) {
            this.node = node;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n ];

        isRoot = new boolean[n + 1];
        nodeArr = new Node[n + 1];
        depthDeque = new Deque[n + 1];

        for (int i = 0; i < n + 1; i++) depthDeque[i] = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int root = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            nodeArr[root] = new Node(root, left, right);
            if (left != -1)
                isRoot[left] = true;
            if (right != -1)
                isRoot[right] = true;
        }

        int root = findRoot();
        dfs(nodeArr[root], nodeArr[root], 0, 1);

        int depth = 0;



        HashMap<Integer, Integer> posMap = new HashMap();
        while (true) {
            Deque<Integer> deque = depthDeque[depth];  
            if (deque.isEmpty()) break;

            
            if (depth == 0) {
                int[] pos = dict.get(root);
                int leftCnt = pos[0];
                int rightCnt = pos[1];

                arr[leftCnt] = root;
                posMap.put(root, leftCnt);
            } else {          
                for (int num : deque) {
                    if (dict.containsKey(num)) {
                        int[] pos = dict.get(num);
                        int leftCnt = pos[0];
                        int rightCnt = pos[1];
                        int cur_root = pos[2];
                        int isLeft = pos[3];
                        int root_idx = posMap.get(cur_root);
                        
                        if (isLeft == 1) {
                            int cur_idx = root_idx - rightCnt - 1;
                            arr[cur_idx] = num;
                            posMap.put(num, cur_idx);
                        } else {
                            int cur_idx = root_idx + leftCnt + 1;
                            arr[cur_idx] = num;
                            posMap.put(num, cur_idx);
                        }  
                        
                    } 
                }
            }
            
            depth++;      
        }
        int width = 1;
        int ans_depth = 1;
        depth = 0;
        
        while(true) {
            Deque<Integer> deque = depthDeque[depth];  
            if (deque.isEmpty()) break;

            if (deque.size() == 1) {
                depth++;
                continue;
            }
            
            int left = deque.pollFirst();
            int right = deque.pollLast();

            int left_idx = posMap.get(left);
            int right_idx = posMap.get(right);

            int cur_width = right_idx - left_idx + 1;

            if (width < cur_width) {
                width = cur_width;
                ans_depth = depth + 1;
            }
            
            depth++;
        }

        System.out.println(ans_depth + " " + width);
    }

    static int dfs(Node node, Node prev, int depth, int isLeft) {
        int left = node.left;
        int right = node.right;
        depthDeque[depth].add(node.node);
        if (left == -1 && right == -1) {
            dict.put(node.node, new int[] {0, 0, prev.node, isLeft});
            return 1;
        }

        int leftCnt = 0;
        int rightCnt = 0;
        if (left != -1) leftCnt = dfs(nodeArr[left], node, depth + 1, 1);
        if (right != -1) rightCnt = dfs(nodeArr[right], node, depth + 1, 0);
        
        // System.out.println(node.node + " " + leftCnt + " "+ rightCnt);
        dict.put(node.node, new int[] {leftCnt, rightCnt, prev.node, isLeft});
        return 1 + leftCnt + rightCnt;
    }
    
    static int findRoot() {
        int root = 0;
        
        for (int i = 1; i < n + 1; i++) {
            if (!isRoot[i]) {
                root = i;
                break;
            }
        }

        return root;
    }
}