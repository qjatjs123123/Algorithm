import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][N + 1];
		
		for (int len = 0; len < N; len++) {
			int max = N - len;
			for (int left = 1; left <= max ; left++) {
				int right = left + len;
				
				if (left == right) {
					dp[left][right] = 1;
					continue;
				}
				
				if ( (arr[left] == arr[right]) && (len == 1) ) {
					dp[left][right] = 1;
					continue;
				}
				
				if ( (arr[left] == arr[right]) && (dp[left + 1][right - 1] == 1) ) {
					dp[left][right] = 1;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			sb.append(dp[left][right]).append("\n");
		}
		System.out.println(sb.toString());
	}
}