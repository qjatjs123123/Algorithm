import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		String str3 = br.readLine();
		
		int N1 = str1.length();
		int N2 = str2.length();
		int N3 = str3.length();
		
		int[][][] dp = new int[N1 + 1][N2 + 1][N3 + 1];
		
		for (int r = 1; r <= N1; r++) {
			for (int c = 1; c <= N2; c++) {
				for (int z = 1; z <= N3; z++) {
					
					if (str1.charAt(r - 1) == str2.charAt(c - 1) && 
							str2.charAt(c - 1) == str3.charAt(z - 1) && 
							str1.charAt(r - 1) == str3.charAt(z - 1)) {
						dp[r][c][z] = dp[r-1][c-1][z-1] + 1;
						continue;
					}
					
					dp[r][c][z] = Math.max(dp[r][c][z - 1], dp[r][c - 1][z]);
					dp[r][c][z] = Math.max(dp[r][c][z], dp[r-1][c][z]);
					
				}
			}
		}
		
		System.out.println(dp[N1][N2][N3]);
	}
	static class Pos {
		int row, col;
		
		Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}