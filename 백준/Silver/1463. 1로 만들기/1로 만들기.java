
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[1000001];
		for (int i = 0; i <= 1000000; i++) dp[i] = Integer.MAX_VALUE;
		
		Deque<int[]> q = new LinkedList<>();
		int[] tmp = {n, 1};
		q.add(tmp);
		
		while (!q.isEmpty()) {
			int[] cur_tmp = q.pollFirst();
			int num = cur_tmp[0];
			int dist = cur_tmp[1];
			if (num == 1) {
				System.out.println(dist-1);
				break;
			}
			
			if (num % 3 == 0 && dp[num / 3] > dist) {
				dp[num / 3] = dist;
				int[] new_tmp = {num / 3, dist + 1};
				q.add(new_tmp);
			}
			if (num % 2 == 0 && dp[num / 2] > dist) {
				dp[num / 2] = dist;
				int[] new_tmp = {num / 2, dist + 1};
				q.add(new_tmp);
			}
			if (num >= 0 && dp[num - 1] > dist) {
				dp[num - 1] = dist;
				int[] new_tmp = {num - 1, dist + 1};
				q.add(new_tmp);
			}
		}
		
	}
}