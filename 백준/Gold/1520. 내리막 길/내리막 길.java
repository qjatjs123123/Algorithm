import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	static int[][] memo;
	static int[][] direction = {
			{1, 0 },
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		memo = new int[N][M];
		
		for (int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				for(int i = 0; i < 4; i++) memo[row][col] = -1;
			}
		}
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				graph[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int[] direct: direction) dp(0,0);
		
		System.out.println(memo[0][0]);
	}
	
	static int dp(int row, int col) {
		
		int total = 0;
		if (row == N -1 && col == M - 1) {
			return 1;
		}
		if (memo[row][col] != -1) return memo[row][col];
		
		for (int[] direct: direction) {
			int new_row = row + direct[0];
			int new_col = col + direct[1];
			
			if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
			if (graph[row][col] <= graph[new_row][new_col]) continue;
			
			total += dp(new_row, new_col);
		}
		
		memo[row][col] = total;
		return total;
	}
}
