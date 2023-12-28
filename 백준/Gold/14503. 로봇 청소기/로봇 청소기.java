import java.util.*;
import java.io.*;

public class Main
{
	static int[][] graph;
	static Point robot;
	static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북동남서
	static int n;
	static int m;
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String[] str1 = br.readLine().split(" ");
		n = Integer.parseInt(str1[0]);
		m = Integer.parseInt(str1[1]);
		String[] str2 = br.readLine().split(" ");
		robot = new Point(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]), Integer.parseInt(str2[2]));
		
		graph = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		int ans = 0;
		
		while (true) {
			//  현재 칸 청소안되어 있으면 현재칸 청소
			if (graph[robot.row][robot.col] == 0) {
				graph[robot.row][robot.col] = 2;
				ans += 1;
			}
			
			// 현재칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if (!check()) {
				int back = (robot.direct + 2) % 4;
				
				int new_row = robot.row + direction[back][0];
				int new_col = robot.col + direction[back][1];
				
				if (graph[new_row][new_col] == 1) break;
				robot.row = new_row;
				robot.col = new_col;
			}else {
				// 현재칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
				for(int i = 0; i< 4; i++) {
					if (robot.direct == 0) robot.direct = 3;
					else robot.direct--;
					
					int new_row = robot.row + direction[robot.direct][0];
					int new_col = robot.col + direction [robot.direct][1];
					
					if (graph[new_row][new_col] == 0) {
						robot.row = new_row;
						robot.col = new_col;
						break;
					}
				}
			}
		}
		System.out.println(ans);
	}
	
	static boolean check() {
		for (int[] direct : direction) {
			int new_row = robot.row + direct[0];
			int new_col = robot.col + direct[1];
			
			if (0 <= new_row && new_row < n && 0 <= new_col && new_col < m
					&& graph[new_row][new_col] == 0) return true;
		}
		return false;
	}
	
	static class Point {
		int row, col, direct;
		
		Point(int r, int c, int d) {
			this.row = r;
			this.col = c;
			this.direct = d;
		}
	}

}