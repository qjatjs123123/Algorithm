import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Character> list1 = new ArrayList<>();
	static ArrayList<Character> list2 = new ArrayList<>();
	static Pos[][] memo;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split("");
		list1.add(' ');
		for (String alpha : tmp) {
			list1.add(alpha.charAt(0));
		}
		
		tmp = br.readLine().split("");
		list2.add(' ');
		for (String alpha : tmp) {
			list2.add(alpha.charAt(0));
		}
		
		memo = new Pos[list1.size()][list2.size()];
		for (int row = 0; row < list1.size(); row++) {
			for (int col = 0; col < list2.size(); col++) {
			}
		}
		
		dp = new int[list1.size()][list2.size()];
		
		for (int row = 1; row < list1.size(); row++) {
			for (int col = 1; col < list2.size(); col++) {
				if (list1.get(row) == list2.get(col)) {
					dp[row][col] = dp[row - 1][col - 1] + 1;
					memo[row][col] = new Pos(row - 1, col - 1);
					continue;
				}
				
				if (dp[row - 1][col] > dp[row][col - 1]) {
					dp[row][col] = dp[row - 1][col];
					memo[row][col] = new Pos(row - 1, col);
				}
				
				else {
					dp[row][col] = dp[row][col - 1];
					memo[row][col] = new Pos(row, col - 1);
				}

			}
		}
		int ans = dp[list1.size()-1][list2.size()-1];
		System.out.println(ans);
		

		if (ans != 0) {
			int cur_row = list1.size() - 1;
			int cur_col = list2.size() - 1;
			String s = "";
			while (true) {
				if (cur_row == 0 || cur_col == 0) break;
				
				Pos pos = memo[cur_row][cur_col];
				
				int gap = (cur_row - pos.row) + (cur_col - pos.col);
				
				if (gap == 2) {
					s += list2.get(cur_col);
				}
				cur_row = pos.row;
				cur_col = pos.col;
				
			}
			
			String reverse = "";
			for (int i = s.length() - 1; i >= 0; i--) reverse += s.charAt(i);
			
			System.out.println(reverse);
		}
		
		
	}
}

class Pos {
	int row, col;

	public Pos(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Pos [row=" + row + ", col=" + col + "]";
	}
	
}

