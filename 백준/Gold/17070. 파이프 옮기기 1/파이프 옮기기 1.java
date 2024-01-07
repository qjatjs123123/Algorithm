
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	static int[][] graph;
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		
		for (int row = 0; row < n; row++) {
			int[] colList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for (int col = 0; col < n; col++) {
				graph[row][col] = colList[col];
			}
		}
		
		int[][][] dp = new int[3][n + 1][n + 1];
		
		dp[0][1][2] = 1;
		for (int row = 1; row < n + 1; row++) {
			for (int col = 3; col < n + 1; col++) {
				if ((row == 1 && col == 1) || graph[row-1][col-1] == 1) continue;
				
				dp[0][row][col] = dp[2][row][col - 1] + dp[0][row][col - 1];
				dp[1][row][col] = dp[2][row - 1][col] + dp[1][row - 1][col];
				if (row >= 2 && graph[row - 2][col-1] == 0 && graph[row-1][col - 2] == 0) {
					dp[2][row][col] = dp[2][row - 1][col - 1] + dp[0][row - 1][col - 1] + dp[1][row - 1][col - 1];
				}
			}
		}
		int ans = 0;
		for (int i = 0; i< 3; i++) ans += dp[i][n][n];
		System.out.println(ans);
	}
}