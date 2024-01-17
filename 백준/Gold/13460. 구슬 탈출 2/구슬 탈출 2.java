
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	static String[][] graph;
	static int ans = 99999;
	static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int n;
	static int m;
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = tmp[0];
		m = tmp[1];
		
		graph = new String[n][m];
		
		for (int row = 0; row < n; row++) {
			String[] colList = br.readLine().split("");
			for (int col = 0; col < m; col++) {
				graph[row][col] = colList[col];
			}
		}
		
		int red_row = 0;
		int red_col = 0;
		int blue_row = 0;
		int blue_col = 0;
		
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < m; col++) {
				if (graph[row][col].equals("B")) {
					blue_row = row;
					blue_col = col;
				}
				if (graph[row][col].equals("R")) {
					red_row = row;
					red_col = col;
				}
			}
		}
		dfs(1, red_row, red_col, blue_row, blue_col);
		if (ans == 99999) System.out.println(-1);
		else System.out.println(ans);
	}
	
	static void dfs(int cnt, int red_row, int red_col, int blue_row, int blue_col) {

		if (cnt > 10) return;
		
		for (int[] direct : direction) {
			boolean red = false;
			boolean blue = false;
			int cur_red_row = red_row;
			int cur_red_col = red_col;
			int cur_blue_row = blue_row;
			int cur_blue_col = blue_col;
			
			while (true) {
				if (cur_red_row + direct[0]< 0 || cur_red_row + direct[0]>= n || 
						cur_red_col + direct[1]< 0 || cur_red_col + direct[1]>= m) break;
				if (graph[cur_red_row + direct[0]][cur_red_col + direct[1]].equals("#")) break;		
				if (graph[cur_red_row + direct[0]][cur_red_col + direct[1]].equals("O")) {
					red = true;
					break;
				}
				cur_red_row += direct[0];
				cur_red_col += direct[1];
			}
			
			while (true) {
				if (cur_blue_row+ direct[0] < 0 || cur_blue_row + direct[0] >= n 
						|| cur_blue_col+ direct[1] < 0 || cur_blue_col+ direct[1] >= m) break;
				if (graph[cur_blue_row + direct[0]][cur_blue_col + direct[1]].equals("#")) break;		
				if (graph[cur_blue_row + direct[0]][cur_blue_col + direct[1]].equals("O")) {
					blue = true;
					break;
				}
				cur_blue_row += direct[0];
				cur_blue_col += direct[1];
			}
			
			if (cur_red_row == cur_blue_row && cur_red_col == cur_blue_col) {
				if (direct[0] == 0 && direct[1] == 1) {
					if (red_col > blue_col) cur_blue_col -= 1;
					else cur_red_col -= 1;
				} else if(direct[0] == 0 && direct[1] == -1) {
					if (red_col < blue_col) cur_blue_col += 1;
					else cur_red_col += 1;
				} else if(direct[0] == 1 && direct[1] == 0) {
					if (red_row < blue_row) cur_red_row -= 1;
					else cur_blue_row -= 1;
				} else {
					if (red_row < blue_row) cur_blue_row += 1;
					else cur_red_row += 1;
				}
				
			}
			
			if (red && !blue) {
				ans = Math.min(cnt, ans);
			}
			
			if (!red && !blue) dfs(cnt + 1,  cur_red_row,  cur_red_col,  cur_blue_row,  cur_blue_col);
		}
		
	}
}