import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static String[][] graph = new String[5][5];
	static ArrayList<int[]> posList = new ArrayList<>();
	static Stack<Integer> stack = new Stack<>();
	static boolean[] visited = new boolean[25];
	static int ans = 0;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int row = 0; row < 5; row++) {
			String[] colList = br.readLine().split("");
			for (int col = 0; col < 5; col++) {
				graph[row][col] = colList[col];
				posList.add(new int[] {row, col});
			}
		}	
		backtracking(0, 0, 0);
		System.out.println(ans);
	}
	
	static void backtracking(int l, int start, int lim) {
		if (l == 7) {
			bfs();
			return;
		}
		
		for (int i = start; i < 25; i++) {
			int[] pos = posList.get(i);
			String team = graph[pos[0]][pos[1]];
			int new_lim = team.equals("Y") ? lim + 1 : lim;
			
			if (visited[i] || new_lim >= 4) continue;
			visited[i] = true;
			stack.push(i);
			backtracking(l + 1, i + 1, new_lim);
			stack.pop();
			visited[i] = false;
		}
	}
	
	static void bfs() {
		boolean[][] cur_visited = new boolean[5][5];
		boolean[][] new_visited = new boolean[5][5];
		
		for (int idx : stack) {
			int[] pos = posList.get(idx);
			int cur_row = pos[0];
			int cur_col = pos[1];
			
			cur_visited[cur_row][cur_col] = true;
		}
		
		int[] pos = posList.get(stack.get(0));
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {pos[0], pos[1]});
		new_visited[pos[0]][pos[1]] = true;
		int total = 0;
		
		while(!deque.isEmpty()) {
			int[] tmp = deque.pollFirst();
			int cur_row = tmp[0];
			int cur_col = tmp[1];
			total += 1;
			for (int[] direct: direction) {
				int new_row = cur_row + direct[0];
				int new_col = cur_col + direct[1];
				
				if (new_row < 0 || new_row >= 5 || new_col < 0 || new_col >= 5 
						|| new_visited[new_row][new_col] || !cur_visited[new_row][new_col]) continue;
				
				new_visited[new_row][new_col] = true;
				deque.addLast(new int[] {new_row, new_col});
			}
		}
		if (total == 7) ans++;
	}
}
