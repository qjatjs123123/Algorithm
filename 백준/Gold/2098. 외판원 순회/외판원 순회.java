import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static int[][] graph;
	static int[][] dp;
	static int MASK;
	static final int MAX_NUM = 100_000_000;
	
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		MASK = (int)Math.pow(2, N) - 2;
		dp = new int[N][MASK + 1];
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col <= MASK; col++ ) {
				dp[row][col] = Integer.MAX_VALUE;
			}
		}
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				graph[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(recursion(0, 0));
	}
	
	static int recursion(int cur_node, int bitmask) {
		
		if (dp[cur_node][bitmask] != Integer.MAX_VALUE) return dp[cur_node][bitmask];
		if ((bitmask & MASK) == MASK) {
			
			if (graph[cur_node][0] != 0) return graph[cur_node][0];
			return MAX_NUM;
		}
		
		int max_num = MAX_NUM;
		for (int new_node = 1; new_node < N; new_node++) {
			if (graph[cur_node][new_node] == 0) continue;
			if ( (bitmask & (1 << new_node)) > 0) continue;
			
			int new_bitmask = bitmask | (1 << new_node);
			
			max_num = Math.min(max_num, recursion(new_node, new_bitmask) + graph[cur_node][new_node]);
		}
		
		dp[cur_node][bitmask] = max_num;
		return max_num;
	}
}
