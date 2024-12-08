import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] graph;
	static boolean[][] visited;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			
			for (int col = 0; col < N; col++) {
				graph[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (graph[row][col] == 1 && !visited[row][col]) {
					Deque<int[]> boundaryList = getBoundary(row, col);
					int cnt = getShortestDistance(boundaryList);
					if (cnt == Integer.MAX_VALUE) continue;
					ans = Math.min(ans, cnt );
					
				}

			}


		}
		System.out.println(ans);
	}
	
	static int getShortestDistance(Deque<int[]> boundaryList) {
		int max_num = Integer.MAX_VALUE;
		boolean[][] cur_visited = new boolean[N][N];
		
		while (!boundaryList.isEmpty()) {
			int[] cur_pos = boundaryList.pollFirst();
			int cur_row = cur_pos[0];
			int cur_col = cur_pos[1];
			int cur_num = cur_pos[2];
			int cur_cnt = cur_pos[3];
			cur_visited[cur_row][cur_col] = true;
			
			for (int[] direct : direction) {
				int new_row = cur_row + direct[0];
				int new_col = cur_col + direct[1];
				
				if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
				if (cur_visited[new_row][new_col] || visited[new_row][new_col]) continue;
				
				if (graph[new_row][new_col] == 0) {
					boundaryList.add(new int[] {new_row, new_col, 0, cur_cnt + 1});

					cur_visited[new_row][new_col] = true;
				}
				if (cur_num == 0 && graph[new_row][new_col] == 1) {
					max_num = Math.min(max_num, cur_cnt);
				}
			}
		}
		

		return max_num;
	}
	
	static Deque<int[]> getBoundary(int row, int col) {
		visited[row][col] = true;
		Deque<int[]> deque = new LinkedList<>();
		Deque<int[]> boundary = new LinkedList<>();
		
		deque.add(new int[] {row, col, 1});
		while(!deque.isEmpty()) {
			int[] cur_pos = deque.pollFirst();
			int cur_row = cur_pos[0];
			int cur_col = cur_pos[1];
			int cur_num = cur_pos[2];
			
			for (int[] direct : direction) {
				int new_row = cur_row + direct[0];
				int new_col = cur_col + direct[1];
				
				if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
				if (visited[new_row][new_col]) continue;
				
				
				if (graph[new_row][new_col] == 0) boundary.add(new int[] {cur_row, cur_col, 1, 0});
				else {
					deque.add(new int[] {new_row, new_col, graph[new_row][new_col]});
					visited[new_row][new_col] = true;
				}
			}
		}
		
		return boundary;
	}
}
