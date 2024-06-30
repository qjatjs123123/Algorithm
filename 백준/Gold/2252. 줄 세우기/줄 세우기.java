import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] count = new int[N + 1];
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());	
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			count[end]++;
			
			if (graph.containsKey(start)) {
				ArrayList<Integer> list = graph.get(start);
				list.add(end);
				graph.put(start, list);	
			}
			else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(end);
				graph.put(start, list);	
			}
		}
		
		Deque<Integer> deque = new LinkedList<>();
		
		for (int i = 1; i < N + 1; i++) {
			if (count[i] == 0) deque.add(i);
		}
		StringBuilder sb = new StringBuilder();
		
		while(!deque.isEmpty()) {
			int cur_num = deque.pollFirst();
			
			sb.append(cur_num);
			sb.append(" ");
			if (!graph.containsKey(cur_num)) continue;
			ArrayList<Integer> list = graph.get(cur_num);
			for(int num: list) {
				count[num]--;
				if (count[num] == 0) deque.add(num);
			}
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (count[i] != 0) sb.append(i);
		}
		
		System.out.println(sb.toString());
	}

}