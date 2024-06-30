import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp;
	static int[][] graph;
	static int MAX_NUM;
	static int visited;
	static int N;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		N = Integer.parseInt(st.nextToken());
		visited = (int)Math.pow(2, N) - 1;
		dp = new int[N][visited + 1];
		graph = new int[N][N];
		MAX_NUM = 100_000_000;
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col <= visited; col++) {
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
	
	static int recursion(int node, int bitmask) {
		 if (dp[node][bitmask] != Integer.MAX_VALUE) return dp[node][bitmask];
		 
		 if (bitmask == visited - 1) {
			 if (graph[node][0] == 0) return MAX_NUM;
			 return graph[node][0];
		 }
		 
		 int max_num = MAX_NUM;
		 for (int col = 1; col < N; col++) {
			 if (graph[node][col] == 0) continue;
			 if ((bitmask & (1 << col)) > 0) continue;
			 
			 int new_bitmask = bitmask | (1 << col);
			 max_num = Math.min(max_num, recursion(col, new_bitmask) + graph[node][col]);
			
		 }
		 
		 dp[node][bitmask] = max_num;
		 return max_num;
	}
}