import java.util.*;
import java.io.*;

public class Main
{
	static HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			String[] tmp = br.readLine().split(" ");
			int start = Integer.parseInt(tmp[0]);
			int end = Integer.parseInt(tmp[1]);
			int value = Integer.parseInt(tmp[2]);
			int[] node = {value, end};
			
			if (graph.containsKey(start)) {		
				graph.get(start).add(node);
			}else {
				ArrayList<int[]> arraylist = new ArrayList<>();
				arraylist.add(node);
				graph.put(start, arraylist);
			}
		}
		
		String[] tmp = br.readLine().split(" ");
		int start = Integer.parseInt(tmp[0]);
		int end = Integer.parseInt(tmp[1]);
		int ans = dijkstra(n, start, end);
		System.out.println(ans);
    }
	
	static int dijkstra(int n, int start, int end) {
		int[] distance = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		for (int i = 0; i < n + 1; i++) {
			distance[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
		int[] first = {0, start};
		pq.add(first);
		visited[start] = true;
		
		while (!pq.isEmpty()) {
			int[] tmp = pq.poll();
			int val = tmp[0];
			int s = tmp[1];
			visited[s] = true;
			if (!graph.containsKey(s)) continue;
			if (val > distance[s]) continue;
			
			ArrayList<int[]> arraylist = new ArrayList<>();
			arraylist = graph.get(s);
			
			for (int[] new_node : arraylist) {
				int new_val = new_node[0];
				int new_start = new_node[1];
				
				if (distance[new_start] > val + new_val) {
					distance[new_start] = val + new_val;
					int[] node = {val + new_val, new_start};
					pq.add(node);
				}
			}
		}
		return distance[end];
	}

	
}
