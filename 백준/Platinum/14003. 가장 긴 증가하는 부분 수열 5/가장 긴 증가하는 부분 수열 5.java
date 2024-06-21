import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] index = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(arr[0]);
		index[0] = 0;
		for (int i = 1; i < N; i++) {
			int idx = lowerBound(arr[i], list);

			if (list.size() == idx) list.add(arr[i]);
			else list.set(idx, arr[i]);
			index[i] = idx;
		}
		
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		System.out.println(list.size());
		
		int target = list.size() - 1;
		for (int i = N - 1; i >= 0; i--) {
			if (target == index[i]) {
				stack.push(arr[i]);
				
				target--;
			}
		}
		
		while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
		System.out.println(sb.toString());
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