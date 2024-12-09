import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int K;
	static int[][] memo;
	static int[] prefix;
	static int[] arr;
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			arr = new int[K];
			for (int row = 0; row < K; row++) arr[row] = Integer.parseInt(st.nextToken());
			memo = new int[K][K];
			prefix = new int[K];
			
			for (int row = 0; row < K; row++) {
				for(int col = 0; col < K; col++) {
					for (int z = 0; z < K; z++) {
						memo[row][col] = -1;
					}
				}
			}
			prefix[0] = arr[0];
			for (int i = 1; i < K; i++) prefix[i] = arr[i] + prefix[i - 1];
			

			System.out.println(dp(0, K - 1));

		}
		
	}
	
	static int dp(int start, int end) {
		if (start == end) return 0;
        if (memo[start][end] != -1) return memo[start][end];
		if (end - start == 1) {
			memo[start][end] = arr[end] + arr[start];
			
			return arr[end] + arr[start];
		}
		int sum = 0;
		if (start == 0) sum = prefix[end];
		else sum = prefix[end] - prefix[start-1];
		
		int total = Integer.MAX_VALUE;
		for (int mid = start; mid < end; mid++) {
			total = Math.min(total, dp(start, mid) + dp(mid + 1, end) + sum);
		}

		memo[start][end] = total;
		return total;
	}

}
