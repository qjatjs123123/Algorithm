import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int[] list1;
	static int[] list2;
	static int n = 0;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		arr = new int[n][4];
		list1 = new int[n*n];
		list2 = new int[n*n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 4; j++) arr[i][j] = Integer.parseInt(st.nextToken());
		}
		

		make(2, list2);
		
		Arrays.sort(list2);

		long ans = 0;
		
		for (int i = 0; i < n; i++) {	
			for (int j = 0; j < n; j++) {
				int temp = arr[i][0] + arr[j][1];
				int l1 = upperBound(list2, temp *  -1);
				int l2 = lowerBound(list2, temp *  -1);
				ans += (l1 - l2);
			}

		}
		

		System.out.println(ans);
	}
	
	static void make(int start, int[] list) {
		int index = 0;
		for (int i = 0; i < n; i++) {	
			for (int j = 0; j < n; j++) {
				list[index++] = arr[i][start] + arr[j][start + 1];
			}

		}
	}
	
	static int upperBound(int[] arr, int key) {
		int start = 0, end = arr.length-1;
		while (start <= end) {
			int mid = (start + end)/2;
			if (arr[mid] > key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
 		}
		return end;
	}
	
	static int lowerBound(int[] arr, int key) {
		int start = 0, end = arr.length-1;
		while (start <= end) {
			int mid = (start + end)/2;
			if (arr[mid] >= key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
 		}
		return end;
	}
}

