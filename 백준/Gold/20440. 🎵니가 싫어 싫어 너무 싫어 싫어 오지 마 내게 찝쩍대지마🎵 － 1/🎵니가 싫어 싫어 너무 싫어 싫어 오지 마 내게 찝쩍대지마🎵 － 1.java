import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, Integer> counter = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if (counter.containsKey(start)) {
				int cnt = counter.get(start);
				counter.put(start, cnt + 1);
			}else {
				counter.put(start, 1);
			}
			
			if (counter.containsKey(end)) {
				int cnt = counter.get(end);
				counter.put(end, cnt - 1);
			}else {
				counter.put(end, -1);
			}
			
		}
		
		Set<Integer> set= counter.keySet();
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int n : set) list.add(n);
		
		Collections.sort(list);
		
		int max_cnt = 0;
		int[] arr = new int[2];
		int cur_cnt = 0;
		boolean flg = false;
		
		for (int n : list) {
			int cnt = counter.get(n);
			cur_cnt += cnt;
			
			if (max_cnt < cur_cnt) {
				max_cnt = cur_cnt;
				arr[0] = n;
				flg = false;
			}
			
			if (max_cnt > cur_cnt && !flg) {
				arr[1] = n;
				flg = true;
			}
		}
		
		System.out.println(max_cnt);
		System.out.println(arr[0] + " " + arr[1]);
		
	}
}