import java.io.*; 
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int ans = 0;
	static int n, m;
	static int[] selected;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		
		selected = new int[m];
		backtracking(0, 0);
		System.out.println(sb.toString());
	}	
	
	static void backtracking(int cnt, int flag) {
		if (cnt == m) {
			String ans = "";
			for (int s : selected) ans += Integer.toString(s) + " ";
			sb.append(ans).append("\n");
			return ;
		}
		
		for (int i = 0; i < n; i++) {
			if ((flag & (1 << i)) != 0) continue;
			selected[cnt] = arr[i];
			backtracking(cnt + 1, flag | 1 << i);
		}
	}
	
}
