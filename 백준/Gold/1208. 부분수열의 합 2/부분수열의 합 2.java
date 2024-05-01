import java.io.*;
import java.util.*;

public class Main {
	static int n, s;
	static int[] arr;
	static ArrayList<Integer> list1 = new ArrayList<>();
	static ArrayList<Integer> list2 = new ArrayList<>();
	
	static ArrayList<Long> case1 = new ArrayList<>();
	static ArrayList<Long> case2 = new ArrayList<>();
	
	static Long ans = (long)0;
	static HashMap<Long, Long> dict = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int mid = n / 2;
		
		for (int i = 0; i < mid; i++) list1.add(arr[i]);
		for (int i = mid; i < n; i++) list2.add(arr[i]);
		long zero = 0;
		backtracking1(0, zero);
		backtracking2(0, zero);
		

		
		for (Long num : case1) {
			Long target = s - num;
			
			if (dict.containsKey(target)) ans += dict.get(target);
		}
		
		System.out.println(ans);
	}
	
	static void backtracking1(int start, Long total) {
		
		for (int i = start; i < list1.size(); i++) {
			case1.add(total + list1.get(i));
			if (total + list1.get(i) == s) ans++; 
			backtracking1(i + 1, total + list1.get(i));
		}
		
	}
	
	static void backtracking2(int start, Long total) {
			
		for (int i = start; i < list2.size(); i++) {
			Long hap = total + list2.get(i);
			case2.add(hap);
			if (hap == s) ans++; 
			if (dict.containsKey(hap)) {
				Long count = dict.get(hap);
				dict.put(hap, count + 1);
			} else {

				dict.put(hap, (long)1);
			}
			
			backtracking2(i + 1, total + list2.get(i));
		}
	}
	
}

