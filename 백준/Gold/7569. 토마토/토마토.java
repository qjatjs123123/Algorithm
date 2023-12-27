import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int m = Integer.parseInt(line[0]);
		int n = Integer.parseInt(line[1]);
		int h= Integer.parseInt(line[2]);
		
		int graph[][][] = new int[h][n][m];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				String[] tmp = br.readLine().split(" ");
				
				for(int k = 0; k < m; k++) {
					graph[i][j][k] = Integer.parseInt(tmp[k]);
				}
			}
		}
		
		int a = bfs(graph, h, n, m);
		if (!check(graph, h,n ,m)) System.out.println(-1);
		else System.out.println(a - 1);
		
		
	}
	
	static boolean check(int[][][] graph, int h, int n, int m) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if (graph[i][j][k] == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	static int bfs(int[][][] graph, int h, int n, int m) {
		int direction[][] = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
		
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if (graph[i][j][k] == 1) {
						q.add(new Point(j, k, i, 1));
					}
				}
			}
		}
		Point point = null;
		int ans = 1;
		while (!q.isEmpty()) {
			point = q.poll();
			
			for (int[] direct : direction) {
				int new_row = point.row + direct[0];
				int new_col = point.col + direct[1];
				int new_height = point.height + direct[2];
				
				if (0 <= new_row && new_row < n && 0 <= new_col && new_col < m 
						&& 0 <= new_height && new_height < h && graph[new_height][new_row][new_col] == 0) {
					graph[new_height][new_row][new_col] = point.cnt + 1;
					q.add(new Point(new_row, new_col, new_height, point.cnt + 1));
					ans = point.cnt + 1;
				}
			}
		}
		return ans;
	}
	
	static class Point {
		int row, col, height, cnt;
		
		Point(int x, int y, int z, int cnt){
			this.row = x;
			this.col = y;
			this.height = z;
			this.cnt = cnt;
		}
	}
}