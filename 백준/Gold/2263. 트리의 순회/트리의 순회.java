import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] inorder;
    static int[] postorder;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        inorder = new int[N];
        postorder = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) inorder[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) postorder[i] = Integer.parseInt(st.nextToken());

        preorder(0, N, 0, N);
    }

    static void preorder(int in_left, int in_right, int post_left, int post_right) {
        if (in_right <= in_left) return;
        
        if (in_right - in_left == 1) {
            System.out.print(inorder[in_left] + " ");
            return;
        }

        int root = postorder[post_right - 1];
        int root_idx = 0;
        
        for (int i = in_left; i < in_right; i++) {
            if (inorder[i] == root) {
                root_idx = i;
                break;
            }
        }

        int leftCnt = root_idx - in_left;
        System.out.print(root + " ");
        preorder(in_left, root_idx, post_left, post_left + leftCnt);
        preorder(root_idx + 1, in_right, post_left + leftCnt, post_right - 1);
    }
}