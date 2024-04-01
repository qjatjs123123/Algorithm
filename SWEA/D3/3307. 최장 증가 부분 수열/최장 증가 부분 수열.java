
import java.io.*;
import java.util.*;



public class Solution {

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			int[] dp = new int[N];
			
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
				for (int j = 0; j < i; j++) {
					if (arr[i] > arr[j]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
			}
			
			int max_num = 0;
			
			for (int num : dp) max_num = Math.max(max_num, num);
			
			System.out.println("#" + t + " " + max_num);
		}
		
		
	}
} // end of class