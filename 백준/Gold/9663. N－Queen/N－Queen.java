import java.io.*;
import java.util.*;


public class Main
{	
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int[][] visited;
	static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	static int ans = 0;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		visited = new int[n][n];
		
		for (int row = 0; row < n ; row ++) {
			for(int col = 0 ; col < n; col++) visited[row][col] = -1;
		}
		backtracking(0);
		System.out.println(ans);
	}
	
	static void backtracking(int row) {
		if (row == n) {
			ans += 1;
			return;
		}
		
		for (int col = 0; col < n; col++) {
			if (visited[row][col] != -1) continue;
			bfs(row, col);	
			backtracking(row + 1);	
			back(row, col);

		}
	}
	
	static void bfs(int row, int col) {
		for (int[] direct : direction) {
			int cur_row = row;
			int cur_col = col;
			while (true) {
				if (cur_row < 0 || cur_row == n || cur_col < 0 || cur_col == n) break;
				if (visited[cur_row][cur_col] == -1) visited[cur_row][cur_col] = row;
				cur_row += direct[0];
				cur_col += direct[1];
			}
			
		}	
	}
	
	static void back(int row, int col) {
		for (int[] direct : direction) {
			int cur_row = row;
			int cur_col = col;
			while (true) {
				if (cur_row < 0 || cur_row == n || cur_col < 0 || cur_col == n) break;
				if (visited[cur_row][cur_col] == row) visited[cur_row][cur_col] = -1;
				cur_row += direct[0];
				cur_col += direct[1];
			}
			
		}	
	}
}