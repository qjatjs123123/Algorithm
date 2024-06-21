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
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(arr[0]);

		for (int i = 1; i < N; i++) {
			int idx = lowerBound(arr[i], list);

			if (list.size() == idx) list.add(arr[i]);
			else list.set(idx, arr[i]);
		}
		
		System.out.println(list.size());
	}
	
	static int lowerBound(int target, ArrayList<Integer> list) {
		int start = 0;
		int end = list.size() - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (target <= list.get(mid)) end = mid - 1;
			else start = mid + 1;
		}
		
		return start;
	}
}