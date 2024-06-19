import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Long> sumA = new ArrayList<>();
	static ArrayList<Long> sumB = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arrA = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			arrA[i] = num;
		}
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int[] arrB = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			arrB[i] = num;
		}
	
		for (int i = 0; i < n; i++) {
			long total = arrA[i];
			sumA.add(total);
			for (int j = i + 1; j < n; j++) {
				total += arrA[j];
				sumA.add(total);
			}
		}
		
		HashMap<Long, Integer> counter = new HashMap<>(); 
		
		for (int i = 0; i < m; i++) {
			long total = arrB[i];
			sumB.add(total);
			
			if (counter.containsKey(total)) {
				int cnt = counter.get(total);
				counter.put(total, cnt + 1);
			}else {
				counter.put(total, 1);
			}
			
			for (int j = i + 1; j < m; j++) {
				total += arrB[j];
				sumB.add(total);
				
				
				if (counter.containsKey(total)) {
					int cnt = counter.get(total);
					counter.put(total, cnt + 1);
				}else {
					counter.put(total, 1);
				}
			}
		}
		
		Collections.sort(sumA);
		Collections.sort(sumB);
		
		long ans = 0;
		for (Long num : sumA) {
			Long target = T - num;
			
			if (counter.containsKey(target)) {
				ans += counter.get(target);
			}
		}
		
		System.out.println(ans);
	}
	
	static int binary_search(Long target) {
		int left = 0;
		int right = sumB.size() - 1;
		int ans = -1;
		while (left <= right) {
			int mid  = (left + right) / 2;
			Long num = sumB.get(mid);
			
			if (num == target) {
				ans = mid;
				break;
			}
			if (num > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return ans;
	}
}

