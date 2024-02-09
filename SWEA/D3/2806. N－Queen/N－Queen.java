import java.io.*;
import java.util.*;


public class Solution
{	
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static boolean[][] visited;
	static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	static int ans = 0;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int tc = Integer.parseInt(st.nextToken());
        
        for(int t = 1 ; t <= tc; t++) {
             st = new StringTokenizer(br.readLine());
            ans  = 0;
        	n = Integer.parseInt(st.nextToken());
            visited = new boolean[n][n];
            backtracking(0);
            System.out.println("#" + t + " " + ans);    
        }
        
	}
	
	static void backtracking(int row) {
		if (row == n) {
			ans += 1;
			return;
		}
		
		for (int col = 0; col < n; col++) {
			if (visited[row][col]) continue;
			ArrayList<int[]> stack = bfs(row, col, true);
			backtracking(row + 1);
			back(stack);
		}
	}
	
	static ArrayList<int[]> bfs(int row, int col, boolean flg) {
		ArrayList<int[]> stack = new ArrayList<>();
		for (int[] direct : direction) {

			Deque<int[]> deque = new LinkedList<>();
			deque.add(new int[] {row + direct[0], col + direct[1]});
			
			while (!deque.isEmpty()) {
				int[] cur_arr = deque.pollFirst();
				int cur_row = cur_arr[0];
				int cur_col = cur_arr[1];
				
				if (cur_row < 0 || cur_row == n || cur_col < 0 || cur_col == n) continue;
				if (!visited[cur_row][cur_col]) stack.add(new int[] {cur_row , cur_col});
				visited[cur_row][cur_col] = flg;
				deque.add(new int[] {cur_row + direct[0] , cur_col + direct[1]});
			}
		}
		visited[row][col] = true;
		stack.add(new int[] {row, col});
		
		return stack;
	}
	
	static void back(ArrayList<int[]> stack) {
		for(int[] arr : stack) {
			int cur_row = arr[0];
			int cur_col = arr[1]; 
			if (cur_row < 0 || cur_row == n || cur_col < 0 || cur_col == n) continue;
			visited[arr[0]][arr[1]] = false;
		}
	}
}