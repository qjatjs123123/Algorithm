import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int[] str = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int n = str[0];
		int k = str[1];
		
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(num);
		
		int[][] dp = new int[n + 1][k + 1];
		
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < k + 1; j++) {
				if (j == 0) dp[i][j] = 0;
				else dp[i][j] = Integer.MAX_VALUE - 1;
			}
		}
		
		for (int row = 1; row < n + 1; row++) {
			for (int col = 1; col < k + 1; col++) {
				if (num[row - 1] > col) {
					dp[row][col] = dp[row - 1][col];
				}else {
					dp[row][col] = Math.min(dp[row][col - num[row - 1]] + 1, dp[row - 1][col]);
				}
			}
		}
		
		if (dp[n][k] == Integer.MAX_VALUE-1) System.out.println(-1);
		else System.out.println(dp[n][k]);
    }
}