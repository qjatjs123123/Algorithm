
import java.io.*;
import java.util.*;
public class Main {
	static Long N;
	static long[][] graph = {
			{1, 1},
			{1, 0}
	};
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		

		
		long[][] ans = recursion(N);
		System.out.println(ans[0][1]);
	}
	
	static long[][] recursion(long depth) {
		
		if (depth == 1) {
			return deepCopy(graph);
		}
		
		long[][] tmp = recursion(depth / 2);
		if (depth % 2 != 0) {
			long[][] arr = productMatrix(tmp, tmp);
			return productMatrix(arr, deepCopy(graph));
		} else {
			return productMatrix(tmp, tmp);
		}
		
	}
	
	static long[][] productMatrix(long[][] arrA, long[][] arrB) {
		long[][] new_arr = new long[2][2];
		
		for (int rowA = 0; rowA < 2; rowA++) {
			for (int colA = 0; colA < 2; colA++) {
				long total = 0;
				
				for (int rowB = 0; rowB < 2; rowB++) total += arrA[rowA][rowB] * arrB[rowB][colA];
				new_arr[rowA][colA] = total % 1000000007;
			}
		}
		
		return new_arr;
	}
	
	static long[][] deepCopy(long[][] originArr) {
		long[][] new_graph = new long[2][2];
		
		for (int row = 0; row < 2; row++) {
			for (int col = 0; col < 2; col++) {
				new_graph[row][col] = originArr[row][col];
			}
		}
		
		return new_graph;
	}
}

