import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String[] tmp = br.readLine().split(" ");
		int n = Integer.parseInt(tmp[0]);
		int k = Integer.parseInt(tmp[1]);
		
		int[] coins = new int[n];
		for (int i = 0; i < n; i++ ) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(coins);
		
		int[] dp = new int[k + 1];
		dp[0] = 1;
		for(int co: coins) {
			for (int coin = 1; coin < k + 1; coin++) {
				if (co <= coin) {
					dp[coin] += dp[coin-co];
				}
			}
		}
		System.out.println(dp[k]);
		
    }

}
