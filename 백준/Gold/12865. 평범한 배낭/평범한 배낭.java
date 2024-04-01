import java.io.*;
import java.util.*;

public class Main {
	static int N, K;

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			list.add(new int[] {W, V});
		}
		
		Collections.sort(list, (a, b) -> a[0] - b[0]);
		
		int[] dp = new int[K + 1];
		
		for (int row = 0; row < N; row++) {
			int[] arr = list.get(row);
			int cur_weight = arr[0];
			int cur_value = arr[1];
			
			for (int col = K; col >= 0; col--) {
				if (col - cur_weight < 0) continue;
				
				dp[col] = Math.max(dp[col], dp[col - cur_weight] + cur_value);
			}
		}
		

		System.out.println(dp[K]);
	}
} // end of class