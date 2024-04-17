import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static Long B;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		graph = new int[N][N];
		
		for (int row = 0; row < N; row++ ) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) graph[row][col] = Integer.parseInt(st.nextToken());
		}
		int[][] ans = recursion(B);
		StringBuilder sb = new StringBuilder();
		
		for (int[] a : ans) {
			for (int aa : a) sb.append(aa).append(" ");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int[][] recursion(Long depth) {
		if (depth == 1) return deepCopy(graph);
		
		int[][] halfArr = recursion(depth / 2);
		if (depth % 2 == 0) {
			return productMatrix(halfArr, halfArr);
		}else {
			int[][] tmp = productMatrix(halfArr, halfArr);
			
			return productMatrix(tmp, deepCopy(graph));
		}
		
	}
	
	static int[][] productMatrix(int[][] arrA, int[][] arrB) {
		int[][] new_arr = new int[N][N];
		
		for (int rowA = 0; rowA < N; rowA++) {
			for (int colA = 0; colA < N; colA++) {
				int total = 0;
				
				for (int rowB = 0; rowB < N; rowB++) total += arrA[rowA][rowB] * arrB[rowB][colA];
				new_arr[rowA][colA] = total % 1000;
			}
		}
		
		return new_arr;
	}
	
	static int[][] deepCopy(int[][] origin) {
		int[][] new_arr = new int[N][N];
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) new_arr[row][col] = origin[row][col] % 1000;
		}
		return new_arr;
	}
}

