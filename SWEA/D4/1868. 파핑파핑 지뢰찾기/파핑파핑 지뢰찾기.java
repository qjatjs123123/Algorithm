import java.io.*;
import java.util.*;

public class Solution {
	static int T, N;
	static char[][] graph;
	static int[][] count;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1},
			{1, 1},
			{1, -1},
			{-1, 1},
			{-1, -1}
	};
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			count = new int[N][N];
			graph = new char[N][N];
			for (int row = 0; row < N; row++) {
				String[] colList = br.readLine().split("");
				
				for (int col = 0; col < N; col++) {
					graph[row][col] = colList[col].charAt(0);
					
					if (graph[row][col] == '*') count[row][col] = -1;
					else count[row][col] = 0;
				}
			}
			
			for (int row = 0; row < N; row++) {			
				for (int col = 0; col < N; col++) {
					if (graph[row][col] == '*') {
						
						for (int[] direct : direction) {
							int new_row = row + direct[0];
							int new_col = col + direct[1];
							
							if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
							if (graph[new_row][new_col] == '*') continue;
							count[new_row][new_col] += 1;
						}
						
					}
				}
			}
			
			int ans = 0;

			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (count[row][col] == 0) {

						Deque<int[]> deque = new LinkedList<>();
						deque.add(new int[] {row, col});
						ans++;
						
						while(!deque.isEmpty()) {
							int[] tmp = deque.pollFirst();
							int cur_row = tmp[0];
							int cur_col = tmp[1];
							count[cur_row][cur_col] = -1;
							
							for (int[] direct : direction) {
								int new_row = cur_row + direct[0];
								int new_col = cur_col + direct[1];
								
								if (new_row < 0 || new_row == N || new_col < 0 || new_col == N) continue;
								if (count[new_row][new_col] == -1 ) continue;
								
								if (count[new_row][new_col] == 0) {
									deque.add(new int[] {new_row, new_col});
									count[new_row][new_col] = -1;
									continue;
								}
								
								count[new_row][new_col] = -1;
							}

						}
						
						
					}
				}
			}
			

			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (count[row][col] != -1) {
						ans++;
						
					}
				}
			}
			
			System.out.println("#" + t + " " + ans);
		}
		
	}
	
}

