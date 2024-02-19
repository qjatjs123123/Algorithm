import java.io.*;
import java.util.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int[] num;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		num = new int[n];
		for (int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int target = num[i] - m;
			int idx = binary_search(target);
			
			int[] cases = new int[] {idx-1, idx, idx+1};
			
			for (int j: cases) {
				if (j < 0 || j >= n) continue;
				
				int gap = num[i] - num[j];
				if (gap >= m) ans = Math.min(ans, gap);
			}
		}
		System.out.println(ans);
	}
	static int binary_search(int target) {
		int start = 0;
		int end = n - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target >= num[mid]) start = mid + 1;
			else end = mid - 1;
		}
		return start;
	}
}
