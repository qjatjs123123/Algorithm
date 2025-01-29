
import java.io.*;
import java.util.*;

public class Main {
	
	static int N = 0;
	static int[] inorder;
	static int[] postorder;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		inorder = new int[N];
		postorder = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) inorder[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) postorder[i] = Integer.parseInt(st.nextToken());
		
		preorder(0, N - 1, 0, N - 1);
		System.out.println(sb.toString());
	}
	
	static void preorder(int in_s, int in_e, int post_s, int post_e) {
		if (in_s > in_e || post_s > post_e) return;
		
		int root = postorder[post_e];
		int root_idx = 0;
		
		for (int i = 0; i < N ; i++) {
			if (inorder[i] == root) {
				root_idx = i;
				break;
			}
		}
		
		int leftCnt = root_idx - in_s;
		sb.append(root).append(" ");
		preorder(in_s, root_idx - 1, post_s, post_s + leftCnt - 1);
		preorder(root_idx + 1, in_e, post_s + leftCnt, post_e - 1);
	}
}
