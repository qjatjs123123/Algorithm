import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] preorder;
    static int[] inorder;
    static int n;
    static int idx = 0;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            idx = 0;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            StringBuilder sbb = new StringBuilder();
            
            preorder = new int[n];
            inorder = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) preorder[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) inorder[i] = Integer.parseInt(st.nextToken());

            postorder(0, n, sbb);
            sb.append(sbb.toString()).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void postorder(int left, int right, StringBuilder sb) {
        if (right - left == 0) {
            return;
        }
        
        int root = preorder[idx++];
        int root_idx = 0;
        
        for (int i = left; i < right; i++) {
            if (inorder[i] == root) {
                root_idx = i;
                break;
            }
        }

        // left
        postorder(left, root_idx, sb);
        postorder(root_idx + 1, right, sb);
        sb.append(root).append(" ");
    }
}