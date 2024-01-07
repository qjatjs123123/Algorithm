
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int n = Integer.parseInt(br.readLine());
		int[][] num = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			num[i][0] = tmp[0];
			num[i][1] = tmp[1];
		}
		Arrays.sort(num, (a, b) -> {
			if (a[0] == b[0]) return a[1] - b[1];
			return a[0] - b[0];
		});
		
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (num[i][1] > num[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		int ans = Arrays.stream(dp).max().getAsInt();

		System.out.println(n - ans);
    }
}