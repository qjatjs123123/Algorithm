import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N, M, L;
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
		
		if (N != 0) {
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		list.add(L);
		
		Collections.sort(list);
		
		ArrayList<Integer> distance = new ArrayList<>();
		
		for (int i = 1; i < list.size(); i++) {
			int gap = list.get(i) - list.get(i - 1);
			distance.add(gap);
		}
		
		int start = 1;
		int end = L - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			int count = 0;
			
			for (int dist : distance) {
				count += dist / mid;
				
				if (dist % mid == 0) count--;
			}
			
			if (count <= M) end = mid - 1;
			else start = mid + 1;
		}
		
		System.out.println(start);
	}
	
}