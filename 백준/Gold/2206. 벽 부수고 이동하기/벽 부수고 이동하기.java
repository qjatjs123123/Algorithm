
import java.io.*;
import java.util.*;

public class Main{
	static int N, M;
	static int[][] graph;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	static int key = 1;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		for (int row = 0; row < N; row++) {
			String[] colList = br.readLine().split("");
			for (int col = 0; col < M; col++) {
				graph[row][col] = Integer.parseInt(colList[col]);
			}
		}

		boolean[][][] visited = new boolean[2][N][M]; 
		
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {0, 0, 0, 0});
		boolean flg= true;
		visited[0][0][0] = true;
		
		while (!deque.isEmpty()) {
			int[] tmp = deque.poll();
			
			int cur_row = tmp[0];
			int cur_col = tmp[1];
			int cur_cnt = tmp[2];
			int cur_key = tmp[3];
			

			if (cur_row == N - 1 && cur_col == M - 1) {
				System.out.println(cur_cnt + 1);
				flg = false;
				break;
			}
			
			for (int[] direct : direction) {
				int new_row = cur_row + direct[0];
				int new_col = cur_col + direct[1];
				
				if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
				if (visited[cur_key][new_row][new_col]) continue;
				
				if (graph[new_row][new_col] == 1 && cur_key == 1) continue;
				
				if (graph[new_row][new_col] == 1) {
					deque.add(new int[] {new_row, new_col, cur_cnt + 1, 1});
					visited[cur_key][new_row][new_col] = true;
				} else {
					deque.add(new int[] {new_row, new_col, cur_cnt + 1, cur_key});
					visited[cur_key][new_row][new_col] = true;
				}
				
				
			}
		}
		
	if (flg) System.out.println(-1); 
	}

}

