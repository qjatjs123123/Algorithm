
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		
		for (int row = 0; row < N; row++) {
			String[] colList = br.readLine().split(" ");
			for (int col = 0; col < M; col++) graph[row][col] = Integer.parseInt(colList[col]);
		}
		
		int time = 0;
		
		while (true) {
			boolean[][] visited = new boolean[N][M];
			boolean[][] hole = new boolean[N][M];
			// 치즈 안 내부 구멍 검사
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (!visited[row][col] && graph[row][col] == 0) 
						decideInsideHole(row, col, visited, hole);
				}
			}

			Stack<int[]> stack = new Stack<>();
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					if (graph[row][col] == 1 && decideRemove(row, col, hole)) {
						stack.push(new int[] {row, col});

					}
				}
			}
			
			if (stack.isEmpty()) break;
			
			while(!stack.isEmpty()) {
				int[] arr = stack.pop();
				graph[arr[0]][arr[1]] = 0;
			}
			time++;

		}
		
		
		System.out.println(time);
	}
	
	static boolean decideRemove(int row, int col, boolean[][] hole) {
		
		int count = 0;
		for(int[] direct: direction) {
			int new_row = row + direct[0];
			int new_col = col + direct[1];
			
			if (graph[new_row][new_col] == 0 && !hole[new_row][new_col]) count++;
		}
		
		if (count >= 2) return true;
		else return false;
	}
	
	static void decideInsideHole(int row, int col, boolean[][] visited, boolean[][] hole) {
		
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {row, col});
		boolean isInside = true;
		Stack<int[]> stack = new Stack<>();
		stack.add(new int[] {row, col});
		visited[row][col] = true;
		
		while (!deque.isEmpty()) {
			int[] tmp = deque.poll();
			int cur_row = tmp[0];
			int cur_col = tmp[1];
			
			for (int[] direct : direction) {
				int new_row = cur_row + direct[0];
				int new_col = cur_col + direct[1];
				
				if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) {
					isInside = false;
					continue;
				}
				if (visited[new_row][new_col] || graph[new_row][new_col] == 1) continue;
				
				visited[new_row][new_col] = true;
				deque.add(new int[] {new_row, new_col});
				stack.add(new int[] {new_row, new_col});
			}
		}
		
		if (isInside) {
			while (!stack.isEmpty()) {
				int[] arr = stack.pop();
				hole[arr[0]][arr[1]] = true;
			}
		}
	}
}

