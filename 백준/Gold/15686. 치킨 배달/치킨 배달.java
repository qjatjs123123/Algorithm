import java.util.*;
import java.io.*;

public class Main
{
	static ArrayList<Point> chicken = new ArrayList<Point>();
	static ArrayList<Point> house = new ArrayList<Point>();
	static ArrayList<ArrayList<Point>> cases = new ArrayList<ArrayList<Point>>();
	static ArrayList<Point> temp = new ArrayList<Point>();
	static int n, m;
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		int[][] graph = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			tmp = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (graph[i][j] == 2) chicken.add(new Point(i, j, 1)); 
				if (graph[i][j] == 1) house.add(new Point(i, j, 1));;
			}
		}
		
		backtracking(0);
		
		int ans = Integer.MAX_VALUE;
		
		for (int i = 0; i< cases.size(); i++) {
			ArrayList<Point> c = cases.get(i);
			
			int hap = 0;
			for (Point h: house) {
				int house_row = h.row;
				int house_col = h.col;
				int total = Integer.MAX_VALUE;
				
				for (Point p: c) {
					int chick_row = p.row;
					int chick_col = p.col;
					total = Math.min(total, Math.abs(house_row - chick_row) + Math.abs(house_col - chick_col));
				}
				hap += total;
			}
					
			
			ans = Math.min(ans, hap);
			
		}
		
		System.out.println(ans);
	
	}
	
	
	static void backtracking(int l) {
		if (temp.size() == m) {
			ArrayList<Point> t = new ArrayList<Point>();
			
			for (Point tt: temp) t.add(tt);
			cases.add(t);
			return ;
		}
		
		for (int i = l; i < chicken.size() ; i++) {
			temp.add(chicken.get(i));
			backtracking(i + 1);
			temp.remove(temp.size() - 1);
		}
	}
	
	static class Point {
		int row, col, cnt;
		
		Point (int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
	
}