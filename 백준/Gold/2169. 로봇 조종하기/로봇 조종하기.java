import java.io.*;

public class Main {

	static int N, M;
	static int[][][] dp;
	static int[][] w;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] sarr = br.readLine().split(" ");
		N = Integer.parseInt(sarr[0]);
		M = Integer.parseInt(sarr[1]);

		dp = new int[N + 1][M + 1][3];
		w = new int[N + 1][M + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -101 * N * M;
			}
		}

		for (int i = 1; i <= N; i++) {
			sarr = br.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				w[i][j] = Integer.parseInt(sarr[j - 1]);
			}
		}

		dp[1][1][0] = dp[1][1][1] = dp[1][1][2] = w[1][1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (j > 1)
					dp[i][j][0] = Math.max(dp[i][j - 1][0], dp[i][j - 1][1]) + w[i][j];
				if (i > 1)
					dp[i][j][1] = Math.max(dp[i - 1][j][0], Math.max(dp[i - 1][j][1], dp[i - 1][j][2])) + w[i][j];
			}
			for (int j = M - 1; j >= 1; j--) {
				dp[i][j][2] = Math.max(dp[i][j + 1][1], dp[i][j + 1][2]) + w[i][j];
			}
		}

		bw.write(Math.max(dp[N][M][0], dp[N][M][1]) + "\n");
		bw.flush();

	}

}