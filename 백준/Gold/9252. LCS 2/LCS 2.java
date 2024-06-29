import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int N1 = str1.length();
		int N2 = str2.length();
		int[][] dp = new int[N1 + 1][N2 + 1];
		Pos[][] track = new Pos[N1 + 1][N2 + 1];
		
		for (int row = 1; row <= N1; row++) {
			for (int col = 1; col <= N2; col++) {
				if (str1.charAt(row - 1) == str2.charAt(col - 1)) {
					dp[row][col] = dp[row - 1][col - 1] + 1;
					track[row][col] = new Pos(row - 1, col - 1);
					continue;
				}
				
				if (dp[row][col - 1] > dp[row - 1][col]) {
					dp[row][col] = dp[row][col - 1];
					track[row][col] = new Pos(row, col - 1);
				} else {
					dp[row][col] = dp[row - 1][col];
					track[row][col] = new Pos(row - 1, col);
				}
			}
		}
		
		System.out.println(dp[N1][N2]);
		
		Stack<Character> stack = new Stack<>();
		
		int cur_row = N1;
		int cur_col = N2;
		
		while (cur_row > 0 && cur_col > 0) {
			if (str1.charAt(cur_row - 1) == str2.charAt(cur_col - 1)) {
				stack.push(str1.charAt(cur_row - 1));
			}
			int new_row = track[cur_row][cur_col].row;
			int new_col = track[cur_row][cur_col].col;
			cur_row = new_row;
			cur_col = new_col;
			
		}
		StringBuilder sb = new StringBuilder();
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());
	}
	
	static class Pos {
		int row, col;
		
		Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}