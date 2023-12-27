import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		
		int[][] dp = new int[s2.length + 1][s1.length + 1];
		
		for (int i = 0; i <= s2.length; i++) {
			for (int j = 0; j <= s1.length; j++) {
				dp[i][j] = 0;
			}
		}
		
		for (int i = 1; i <= s2.length; i++) {
			for (int j = 1; j <= s1.length; j++) {
				if (s1[j-1] == s2[i-1]) {
					dp[i][j] = Math.max(dp[i-1][j-1] + 1, dp[i-1][j]);
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
				
			}
		}
		System.out.println(dp[s2.length][s1.length]);
	}
	
}