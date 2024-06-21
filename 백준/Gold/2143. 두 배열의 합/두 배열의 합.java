import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int T = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine());	
		int n = Integer.parseInt(st.nextToken());
		
		int[] arr1 = new int[n];
		st = new StringTokenizer(br.readLine());	
		for (int i = 0; i < n; i++) arr1[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());	
		int m = Integer.parseInt(st.nextToken());
		
		int[] arr2 = new int[m];
		st = new StringTokenizer(br.readLine());	
		for (int i = 0; i < m; i++) arr2[i] = Integer.parseInt(st.nextToken());
		
		ArrayList<Long> list1 = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			long total = 0;
			for (int j = i; j < n; j++) {
				total += arr1[j];
				list1.add(total);
			}
		}
		
		ArrayList<Long> list2 = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			long total = 0;
			for (int j = i; j < m; j++) {
				total += arr2[j];
				list2.add(total);
			}
		}
		
		Collections.sort(list2);
		
		long ans = 0;
		for (Long num : list1) {
			Long target = T - num;
			int up = upperBound(target, list2);
			int lo = lowerBound(target, list2);
			
			ans += (up - lo);
		}
		System.out.println(ans);
	}
	
	static int upperBound(long target, ArrayList<Long> list) {
		int start = 0;
		int end = list.size() - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target >= list.get(mid)) start = mid + 1;
			else end = mid - 1;
		}
		
		return end;
	}
	
	static int lowerBound(long target, ArrayList<Long> list) {
		int start = 0;
		int end = list.size() - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target > list.get(mid)) start = mid + 1;
			else end = mid - 1;
		}
		
		return end;
	}
}
