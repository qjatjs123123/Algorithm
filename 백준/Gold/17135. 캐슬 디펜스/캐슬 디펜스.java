import java.io.*;
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int n, m, d;
	static int[][] graph;
	static int ans = 0;
	static ArrayList<int[]> stack = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		int[][] tmp = new int[n + 1][m];
		graph = new int[n + 1][m];
		for (int row = 0; row < n; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < m; col++) {
				graph[row][col] = Integer.parseInt(st.nextToken());
				tmp[row][col] = graph[row][col];
			}
		}

		
		for (int one = 0; one < m - 2; one++) {
			for (int two = one + 1; two < m - 1; two++) {
				for (int three = two + 1; three < m; three++) {
					int total = 0;
					for (int round = 0; round < n; round++) {
						findAttackEnemy(n - round, one);
						findAttackEnemy(n - round, two);
						findAttackEnemy(n - round, three);
						for (int[] arr : stack) {
							if (graph[arr[0]][arr[1]] == 1) {
								graph[arr[0]][arr[1]] = 0;
								total += 1;
							}
							
						}

						
						stack.clear();


					}
					
					ans = Math.max(ans, total);
					deepcopy(tmp);
				}
			}
		}
		System.out.println(ans);
	}	
	
	static void deepcopy(int[][] tmp) {
		for (int row = 0; row <= n; row++) {
			for(int col = 0; col < m; col++) {
				graph[row][col] = tmp[row][col];
			}
		}
	}
	
	static void findAttackEnemy(int shooterRow, int shooterCol) {
		
		boolean flg = false;
		for (int range = 0; range < d; range++) {

			//left
			for (int col = shooterCol - range; col < shooterCol; col++) {
				int row = shooterRow - (range - (shooterCol - col)) - 1;
				if (col < 0 || row < 0) continue;
				
				if (graph[row][col] == 1) {
					stack.add(new int[] {row, col});
					flg = true;
					break;
				}
			}
			//middle
			if (flg) break;
			int row = shooterRow - range - 1;
			int col = shooterCol;
			
			if (row >= 0 && graph[row][col] == 1) {

				stack.add(new int[] {row, col});
				flg = true;
			}
			
			//right
			if (flg) break;
			for (col = shooterCol + 1; col <= shooterCol + range; col++) {
				int r = shooterRow - (range - (col - shooterCol)) - 1;
				if (col >= m || r < 0) continue;
				if (graph[r][col] == 1) {
					stack.add(new int[] {r, col});
					flg = true;
					break;
					
				}
			}
			if (flg) break;
		}
	}
}
