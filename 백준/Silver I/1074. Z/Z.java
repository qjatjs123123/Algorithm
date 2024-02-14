import java.io.*;
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int r;
	static int c;
	static int[][] graph;
	static int ans = 0;
	static int cnt = 0;
	static int[][] direction = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n];
		recursion(0, 0, (int)Math.pow(2, n));
		System.out.println(ans);
	}
	
	static void recursion(int row, int col, int size) {
		
		if (size == 1) {
			return;
		}
		
		int half = size / 2;
		int cnt = half*half;
		if (row <= r && r < row + half && col <= c && c < col + half) {
			recursion(row, col, half);
		}
		else if (row <= r && r < row + half && col + half <= c && col + 2*half > c) {
			ans += cnt;
			recursion(row, col + half, half);
		}
		else if (row + half <= r && row + half*2 > r && col <= c && c < col + half) {
			ans += cnt*2;
			recursion(row + half, col, half);
		}
		else if (row + half <= r && r < row + 2*half && col + half <= c && col + 2*half > c) {
			ans += cnt*3;
			recursion(row + half, col + half, half);
		}
	}
}
