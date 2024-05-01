import java.io.*;
import java.util.*;

public class Main {
	static char[] list;
	static boolean[][] isPalindrome;
	static int N;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split("");
		N = arr.length;
		
		list = new char[N];
		isPalindrome = new boolean[N][N];
		
		for (int i = 0; i < N; i++) list[i] = arr[i].charAt(0);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int k = j + i;
				if (k >= N) break;
				
				if (j == k) {
					isPalindrome[j][k] = true;
					continue;
				}

				if (list[j] == list[k] ) {
					if (k - j == 1) {
						isPalindrome[j][k] = true;
					}
					
					if (isPalindrome[j + 1][k - 1]) isPalindrome[j][k] = true;
					
				}
			}
		}
		int[] dp = new int[N + 1];
		
		for (int i = 0 ; i < N + 1; i++) dp[i] = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			if (isPalindrome[0][i]) {
				dp[i] = 1;
				continue;
			}
			
			for (int j = 0; j < i; j++) {
				int gap = isPalindrome[i-j][i] ? 1 : j + 1;
				
				dp[i] = Math.min(dp[i], dp[i - j - 1] + gap);
			}
		}

		System.out.println(dp[N-1]);
	}
}

