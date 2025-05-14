import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static Node[] nodeArr;
    static ArrayList<Integer>[] graph = new ArrayList[50_001];
    static int[] parents = new int[50_001];
    static int[] depths = new int[50_001];
    static class Node {
        Node root;
        int value;
        int depth = 0;
        
        Node(Node root, int value){
            this.root = root;
            this.value = value;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < 50_001; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            graph[num1].add(num2);
            graph[num2].add(num1);
 

        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        boolean[] visited1 = new boolean[50_001];
        visited1[1] = true;
        while(!deque.isEmpty()) {
            int num = deque.pollFirst();

            ArrayList<Integer> childList = graph[num];

            for (int child : childList) {
                if (visited1[child]) continue;
                
                if (parents[child] == 0) {
                    parents[child] = num;
                    visited1[child] = true;
                    depths[child] = depths[num] + 1;
                    deque.add(child);
                }
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb  = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            
            int from_depth = depths[num1];
            int to_depth = depths[num2];
            
            if (from_depth > to_depth) {
                int cnt = from_depth - to_depth;

                for (int j = 0; j < cnt; j++) {
                    num1 = parents[num1];
                }
            } else {
                int cnt = to_depth - from_depth;

                for (int j = 0; j < cnt; j++) {
                    num2 = parents[num2];
                }
            }
            int cnt1 = depths[num1];

            
            for (int j = 0; j <= cnt1; j++) {
                if (num1 == num2) {
                    sb.append(num1).append("\n");

                    break;
                } 

                num1 = parents[num1];
                num2 = parents[num2];
            }

        }

        System.out.println(sb.toString());
    }
}