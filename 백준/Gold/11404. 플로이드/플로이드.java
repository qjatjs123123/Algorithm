
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N + 1][N + 1];
		
		for (int row = 0; row <= N; row++) {
			for (int col = 0; col <= N; col++) {
				if (row == col) continue;
				
				if (graph[row][col] == 0) graph[row][col] = Integer.MAX_VALUE;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a][b] = Math.min(graph[a][b], c);
		}
		
		
		floyd();
		StringBuilder sb = new StringBuilder();
		
		for (int row = 1; row <= N; row++ ) {
			for (int col = 1; col <= N; col++) {
				if (graph[row][col] == Integer.MAX_VALUE) sb.append("0").append(" ");
				else sb.append(graph[row][col]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void floyd() {
		for (int mid = 1; mid <= N; mid++) {
			for (int from = 1; from <= N; from++) {
				if (mid == from) continue;
				for (int to = 1; to <= N; to++) {
					if (mid == to) continue;
					
					int pathA = graph[from][mid];
					int pathB = graph[mid][to];
					
					if (pathA == Integer.MAX_VALUE || pathB == Integer.MAX_VALUE) continue;
					graph[from][to] = Math.min(graph[from][to], pathA + pathB);
				}
			}
		}
	}
}

