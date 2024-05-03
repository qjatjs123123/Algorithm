import java.io.*;
import java.util.*;


public class Main {
	static int N, M;
	static int[] count;
	static StringBuilder sb = new StringBuilder();
	static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		count = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (graph.containsKey(a)) {
				ArrayList<Integer> tmp = graph.get(a);
				tmp.add(b);
				graph.put(a, tmp);
			} else {
				ArrayList<Integer> tmp = new ArrayList<>();
				tmp.add(b);
				graph.put(a, tmp);
			}
			count[b] += 1;
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			if (count[i] == 0) pq.add(i);
		}
		
		while (!pq.isEmpty()) {
			int num = pq.poll();
			sb.append(num).append(" ");
			
			ArrayList<Integer> list = graph.get(num);
			if (!graph.containsKey(num)) continue;
			
			for (int new_num : list) {
				count[new_num] -= 1;
				
				if (count[new_num] == 0) pq.add(new_num);
			}
			
		}
		
		System.out.println(sb.toString());
	}
}
