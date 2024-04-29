import java.io.*;
import java.util.*;

public class Main {
	static int n, m, r;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		int[] count = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			count[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new int[n + 1][n + 1];
		
		for (int row = 0; row <= n; row++) {
			for (int col = 0; col <= n; col++) {
				if (row == col) graph[row][col] = 0;
				else {
					graph[row][col] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph[from][to] = Math.min(graph[from][to], dist);
			graph[to][from] = Math.min(graph[to][from], dist);
		}
		
		floyd();
		
		int ans = 0;
		
		for (int row = 1; row <= n; row++) {
			int total = 0;
			for(int col = 1; col <= n; col++) {
				if (graph[row][col] <= m) total += count[col];
			}
			
			ans = Math.max(ans, total);
		}
		System.out.println(ans);
	}
	
	static void floyd() {
		for (int mid = 1; mid <= n; mid++) {
			for (int from = 1; from <= n; from++) {
				if (mid == from) continue;
				for (int to = 1; to <= n; to++) {
					if (from == to) continue;
					
					if (graph[from][mid] == Integer.MAX_VALUE || graph[mid][to] == Integer.MAX_VALUE) continue;
					graph[from][to] = Math.min(graph[from][to], graph[from][mid] + graph[mid][to]);
				}
			}
		}
	}
}

