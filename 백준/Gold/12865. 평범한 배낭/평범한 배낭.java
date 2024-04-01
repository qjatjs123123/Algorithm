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
		
		int[][] dp = new int[N + 1][K + 1];
		
		for (int row = 1; row <= N; row++) {
			int[] arr = list.get(row - 1);
			int cur_weight = arr[0];
			int cur_value = arr[1];
			
			for (int col = 0; col <= K; col++) {
				if (cur_weight > col) {
					dp[row][col] = dp[row - 1][col];
					continue;
				}
				
				dp[row][col] = Math.max(dp[row - 1][col], dp[row - 1][col - cur_weight] + cur_value);
			}
		}
		
		System.out.println(dp[N][K]);
	}
} // end of class