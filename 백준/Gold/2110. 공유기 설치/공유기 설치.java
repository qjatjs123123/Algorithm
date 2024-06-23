import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, C;
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> house = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			house.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(house);
		
		int start = 1;
		int end = 1_000_000_000;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			int count = 1;
			int cur_house = house.get(0);
			int cur_idx = 0;
			// mid 범위 계산
			while (true) {
				int target = cur_house + mid;
				
				int new_idx = upperBound(target, house);
				
				if (new_idx == house.size()) break;
				count++;
				cur_idx = new_idx;
				cur_house = house.get(new_idx);
			}
			
			if (count < C) end = mid - 1;
			else start = mid + 1;
			
		}
		System.out.println(start);
	}
	
	static int upperBound(int target, ArrayList<Integer> list) {
		int start = 0;
		int end = list.size() - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target >= list.get(mid)) start = mid + 1;
			else end = mid - 1;
		}
		
		return start;
	}
	
	
	
}