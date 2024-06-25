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
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);

		int ans = Integer.MAX_VALUE;
		int result = 0;
		
		int left = 0;
		int right = N - 1;
		
		while (left < right) {
			int sum = arr[left] + arr[right];
			
			if (ans > Math.abs(sum)) {
				ans = Math.abs(sum);
				result = sum;
			}
			
			if (sum < 0) {
				left++;
			}
			
			else if (sum > 0) {
				right--;
			}
			else {
				result = 0;
				break;
			}
			
		}
		
		System.out.println(result);
	}
	
	static int change(int num, int[] arr) {
		if (num < 0) return 0;
		if (num == arr.length) return arr.length - 1;
		else return num;
	}
	
	static int upperBound(int target, int[] arr) {
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target >= arr[mid]) start = mid + 1;
			else end = mid - 1;
		}
		
		return start;
	}
	
	static int lowerBound(int target, int[] arr) {
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target > arr[mid]) start = mid + 1;
			else end = mid - 1;
		}
		
		return start;
	}
}