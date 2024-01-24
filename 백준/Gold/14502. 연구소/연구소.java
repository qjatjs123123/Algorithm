
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	static int n;
	static int m;
	static int[][] graph;
	static ArrayList<int[]> virus = new ArrayList<>();
	static int[][] direction = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = tmp[0];
		m = tmp[1];
		graph = new int[n][m];
		for (int row = 0; row < n; row++) {
			String[] colList = br.readLine().split(" ");
			for (int col = 0; col < m; col++) graph[row][col] = Integer.parseInt(colList[col]);
		}
		
		for(int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (graph[row][col] == 2) {
					int[] arr = {row, col};
					virus.add(arr);
				}
			}
		}
		
		backtracking(0);
		System.out.println(ans);
	}
	
	static void backtracking(int depth) {
		
		if (depth == 3) {
			int[][] virusResult = virusMove();
			ans = Math.max(ans, countSafeArea(virusResult));
			return;
		}
		
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (graph[row][col] == 0) {
					graph[row][col] = 1;
					backtracking(depth + 1);
					graph[row][col] = 0;
				}
			}
		}
	}
	
	static int[][] virusMove() {
		Deque<int[]> deque = new LinkedList<>();
		int[][] new_graph = new int[n][m];
		
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) new_graph[row][col] = graph[row][col];
		}
		
		for (int[] v : virus) deque.addLast(v);
		
		while (!deque.isEmpty()) {
			int[] cur_virus = deque.pollFirst();
			
			for (int[] direct : direction) {
				int new_row = cur_virus[0] + direct[0];
				int new_col = cur_virus[1] + direct[1];
				
				if (new_row >= 0 && new_row < n && new_col >= 0 && new_col < m && new_graph[new_row][new_col] == 0) {
					int[] tmp = {new_row, new_col}; 
					new_graph[new_row][new_col] = 2;
					deque.addLast(tmp);
				}
			}
		}	
		return new_graph;
	}
	
	static int countSafeArea(int[][] virusResult) {
		int result = 0;
		
		for (int row = 0; row < n; row++) {
			for(int col = 0; col < m; col++) {
				if (virusResult[row][col] == 0) result += 1;
			}
		}
		
		return result;
	}
}