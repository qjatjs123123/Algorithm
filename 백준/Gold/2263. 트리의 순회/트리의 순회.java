import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] inorder;
    static int[] postorder;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        inorder = new int[N];
        postorder = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        recursion(0, N - 1, 0, N - 1);
        System.out.println(sb.toString());
    }

    static void recursion(int in_left, int in_right, int post_left, int post_right) {
        if (in_left > in_right || post_left > post_right) return;
        int root = postorder[post_right];
        int root_idx = 0;

        for (int i = in_left; i <= in_right; i++) {
            if (inorder[i] == root) {
                root_idx = i;
                break;
            }
        }

        int len = root_idx - in_left;
        sb.append(root).append(" ");
        recursion(in_left, root_idx - 1, post_left, post_left + len - 1);
        recursion(root_idx + 1, in_right, post_left + len, post_right - 1);
    }
}