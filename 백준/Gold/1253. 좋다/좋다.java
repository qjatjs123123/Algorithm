import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0 ; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		int ans = 0;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i == j) continue;
				int target = arr[i] - arr[j];
				int l = lowerBound(target, arr);
				int u = upperBound(target, arr);
				int cnt = 0;
				
				if (i >= l && i < u) cnt++;
				if (j >= l && j < u) cnt++;
				
				if (cnt == (u - l)) continue;
				
				if (0 > l || l == arr.length) continue;
				if (arr[l] == target) {
					ans++;
					break;
				}
			}
			
		}
		
		System.out.println(ans);
	}
	
	static int lowerBound(int target, int[] arr) {
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target <= arr[mid]) end = mid - 1;
			else start = mid + 1;
		}
		
		return start;
	}
	
	static int upperBound(int target, int[] arr) {
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target < arr[mid]) end = mid - 1;
			else start = mid + 1;
		}
		
		return start;
	}
}