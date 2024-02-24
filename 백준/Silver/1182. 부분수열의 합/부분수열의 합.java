import java.io.*; 
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int ans = 0;
	static int n, m;
	static int[] arr;

	
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0 ; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		backtracking(-1, 0);
		System.out.println(ans);
	}	
	
	static void backtracking(int start, int total) {
		
		for (int i = start + 1; i < n; i++) {
			if (total + arr[i] == m) ans += 1;
			backtracking(i, total + arr[i]);
		}
	}
	
}
