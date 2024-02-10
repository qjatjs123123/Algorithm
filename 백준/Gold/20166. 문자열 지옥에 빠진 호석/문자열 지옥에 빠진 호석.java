import java.io.*;
import java.util.*;


public class Main
{	
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int k;
	static String[][] arr;
	static HashMap<Integer, int[]> position = new HashMap<>();
	static HashMap<String, Integer> memo = new HashMap<>();
	static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	static String[] str;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new String[n+2][m+2];
		
		for (int row = 1; row <= n; row++) {
			String tmp[] = br.readLine().split("");
			for (int col = 1; col <= m; col++) {
				arr[row][col] = tmp[col-1];
				position.put(row * 100 + col, new int[] {row, col});
			}
		}


		//초기화
		for (int col = 1; col <= m; col++) {
			position.put(0 * 100 + col, new int[] {n, col});
			position.put((n + 1) * 100 + col, new int[] {1, col});
		}
		
		for (int row = 1; row <= n; row++) {
			position.put(row * 100, new int[] {row, m});
			position.put(row * 100 + (m + 1), new int[] {row, 1});
		}
		
		position.put(0, new int[] {n, m});
		position.put((n+1)*100 + (m + 1), new int[] {1, 1});
		position.put(m + 1, new int[] {n, 1});
		position.put((n+1)*100, new int[] {1, m});

		for (int i = 0; i < k; i++) {
			int ans = 0;
			str = br.readLine().split("");
			String s = String.join("", str);
			
			if (memo.containsKey(s)) {
				System.out.println(memo.get(s));
				continue;
			}
			for (int row = 1; row <= n; row++) {
				for (int col = 1; col <= m; col++) {
					if (arr[row][col].equals(str[0])) ans += dfs(1, row, col);
				}
			}
			memo.put(s, ans);
			System.out.println(ans);

		}
		
	}
	
	static int dfs(int depth, int row, int col) {
		
		if (depth == str.length) {
			return 1;
		}
		int total = 0;
		for (int[] direct : direction) {
			int new_row = row + direct[0];
			int new_col = col + direct[1];
			int new_hash = new_row*100 + new_col;
			
			new_row = position.get(new_hash)[0];
			new_col = position.get(new_hash)[1];
			if (arr[new_row][new_col].equals(str[depth])) {
				total += dfs(depth + 1, new_row, new_col);
			}
		}

		return total;
	}
	
	

}