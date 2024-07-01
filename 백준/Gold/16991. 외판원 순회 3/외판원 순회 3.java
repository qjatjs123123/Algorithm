import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static Pos[] posArr;
	static int MASK;
	static double[][] dp;
	static double[][] graph;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		N = Integer.parseInt(st.nextToken());
		
		posArr = new Pos[N];
		MASK = (1 << N) - 1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());	
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			posArr[i] = new Pos(x, y);
		}
		
		graph = new double[N][N];
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (row == col) {
					graph[row][col] = 0;
					continue;
				}
				
				Pos start = posArr[row];
				Pos end = posArr[col];
				
				double dist = Math.sqrt( Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2) );
				graph[row][col]= dist;
			}
		}
		
		dp = new double[N][MASK + 1];
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col <= MASK; col++) {
				dp[row][col] = Double.MAX_VALUE;
			}
		}
		System.out.println(recursion(0, 0));
	}
	
	static double recursion(int cur_node, int bitmask) {
		if (dp[cur_node][bitmask] != Double.MAX_VALUE) return dp[cur_node][bitmask];
		if (bitmask == MASK - 1) {
			if (graph[cur_node][0] == 0) return 100_000_000;
			return graph[cur_node][0];
		}
		
		double MAX_NUM = 100_000_000;
		
		for (int new_node = 1; new_node < N; new_node++) {
			if ( (bitmask & (1 << new_node)) > 0 ) continue;
			if (graph[cur_node][new_node] == 0) continue;
			int new_bit = bitmask | (1 << new_node);
			
			MAX_NUM = Math.min(MAX_NUM, recursion(new_node, new_bit) + graph[cur_node][new_node]);
		}
		
		dp[cur_node][bitmask] = MAX_NUM;
		return MAX_NUM;
	}
	
	static class Pos {
		int x, y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}