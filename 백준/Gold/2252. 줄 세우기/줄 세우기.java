import java.io.*;
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int n, m;
	
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n+1];
		boolean[] visited = new boolean[n+1];
		arr[0] = 1;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		
		for (int i = 0; i < n + 1; i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			graph.add(tmp);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph.get(start).add(end);
			arr[end] += 1;

		}
		
		Deque<Integer> deque = new LinkedList<>();
		
		for (int i = 1; i <= n; i++) {
			if (arr[i] == 0) {
				visited[i] = true;
				deque.addLast(i);
			}
		}
		while (!deque.isEmpty()) {
			int cur_node = deque.pollFirst();
			
			sb.append(cur_node + " ");
			visited[cur_node] = true;
			
			ArrayList<Integer> new_node_list = graph.get(cur_node);
			
			for (int i = 0 ; i < new_node_list.size(); i++) {
				int new_node = new_node_list.get(i);
				if (new_node >= 1) arr[new_node]--;
				if (arr[new_node] == 0) {
					deque.addLast(new_node);
					new_node_list.remove(i);
					i--;
				}
			}

		}
		
		for (int i = 1; i <= n; i++) {
			if (!visited[i]) sb.append(i + " ");
		}
		
		System.out.println(sb.toString());
	}	
	
}
