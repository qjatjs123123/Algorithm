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
		
		long dp[][] = new long[k + 1][n + 1];
		
		for (int row = 1; row < k + 1; row++) {
			for (int col = 0; col < n + 1; col++) {
				if (row == 1) {
					dp[row][col] = 1;
					continue;
				}
				long total = 0;
				for (int i = 0; i <= col; i++) {
					total += dp[row-1][i];
				}
				dp[row][col] = total % 1000000000;
			}
		}
		System.out.println(dp[k][n] % 1000000000);
    }
}