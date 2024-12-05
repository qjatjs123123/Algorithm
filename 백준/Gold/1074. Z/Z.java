import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, r, c;
	static int ans = 0;
	static boolean flg = false;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		recursion(0, 0, N);
		
		System.out.println(ans);
	}
	
	static void recursion(int row, int col, int depth) {
		int LENGTH = (int)Math.pow(2, depth);
		int half = LENGTH / 2;
		if (flg) return;
		
		if (row == r && col == c) {
			flg = true;
			return;
		}
		if (depth == 0) {
			ans++;
			return;
		}
		// 해당 좌표가 범위 안에 있는지 확인
		if (!isInsideRange(row, col, LENGTH)) {
			ans += LENGTH*LENGTH;
			return;
		}
		
		recursion(row, col, depth - 1);
		recursion(row, col + half, depth - 1);
		recursion(row + half, col, depth - 1);
		recursion(row + half, col + half, depth - 1);

	}
	
	static boolean isInsideRange(int row, int col, int LENGTH) {
		int end_row = row + LENGTH;
		int end_col = col + LENGTH;
		if (row <= r && end_row > r &&
				col <= c && end_col > c) return true;
		return false;
	}

}
