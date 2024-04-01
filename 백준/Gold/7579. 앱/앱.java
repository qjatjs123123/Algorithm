import java.io.*;
import java.util.*;


public class Main {
	static int N, M;

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> list = new ArrayList<>();
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int memory = Integer.parseInt(st1.nextToken());
			int cost = Integer.parseInt(st2.nextToken());
			
			list.add(new int[] {memory, cost});
		}

		Collections.sort(list, (a, b) -> a[0] - b[0]);
		
		int total = getTotal(list);
		
		int[][] dp = new int[N + 1][total + 1];
		
		for (int row = 1; row <= N; row++ ) {
			int[] cur_arr = list.get(row - 1);
			int cur_memory = cur_arr[0];
			int cur_cost = cur_arr[1];
			boolean flg = false;
			for (int col = 0; col <= total; col++) {
				if (cur_cost > col) {
					dp[row][col] = dp[row - 1][col];
					continue;
				}
				
				dp[row][col] = Math.max(dp[row - 1][col], dp[row-1][col - cur_cost] + cur_memory);

			
			}
		}
		
		for (int col = 0; col <= total; col++) {
			if (dp[N][col] >= M) {
				System.out.println(col);
				break;
			}
		}
		
	}
	
	static int getTotal(ArrayList<int[]> arr) {
		int total = 0;
		
		for (int[] num : arr) total += num[1];
		
		return total;
	}
} // end of class