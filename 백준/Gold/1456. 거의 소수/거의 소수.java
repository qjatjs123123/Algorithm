import java.io.*;
import java.util.*;


public class Main
{	
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		Boolean[] prime = new Boolean[10000001];
		for (int i = 2; i < 10000001; i++) {
			prime[i] = true;
		}
		
		for (int i = 2; i < 10000001; i++) {
			if (prime[i]) {
				for (int j = i + i; j < 10000001; j += i) {
					prime[j] = false;
				}
			}
		}
		
		int max_num = (int)Math.ceil(Math.sqrt((double)b));

		int ans = 0;
		for (int i = 2; i <= max_num; i++) {
			if (prime[i]) {
				long n = (long)i*i;
				while (true) {
					if (n > b) break;
					if (a <= n && n <= b) ans += 1;
					if (n > b / i) break;
					n *= (long)i;
				}
			}
		}
		System.out.println(ans);
	}
}