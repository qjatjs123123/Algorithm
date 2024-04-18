
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int[] increase_dp = new int[N];
		int[] decrease_dp = new int[N];
		for (int i = 0; i < N; i++) {
			increase_dp[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (arr[i] > arr[j]) increase_dp[i] = Math.max(increase_dp[i], increase_dp[j] + 1);
			}	
		}
		
		for (int i = N - 1; i >= 0; i--) {
			decrease_dp[i] = 1;
			
			for (int j = i + 1; j < N; j++) {
				if (arr[i] > arr[j]) decrease_dp[i] = Math.max(decrease_dp[i], decrease_dp[j] + 1);
			}
		}
		
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, increase_dp[i]);
			for (int j = i + 1; j < N; j++) {
				if (arr[i] > arr[j]) ans = Math.max(ans, increase_dp[i] + decrease_dp[j]);
			}
		}
		
		System.out.println(ans);
	}
}

