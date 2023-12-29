import java.util.*;
import java.io.*;

public class Main
{
	static HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
	public static void main(String args[]) throws Exception
	{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			String[] str = br.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			int value = Integer.parseInt(str[2]);
			int[] node = {value, end};
			
			if (graph.containsKey(start)) {
				graph.get(start).add(node);
			} else {

				ArrayList<int[]> arraylist = new ArrayList<>();
				arraylist.add(node);
				graph.put(start, arraylist);
			}			
		}
		String[] str = br.readLine().split(" ");
		int start = Integer.parseInt(str[0]);
		int end = Integer.parseInt(str[1]);
		
		System.out.println(dijkstra(n, start, end));
			
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
		while (!pq.isEmpty()) {
			
			int[] cur_arr = pq.poll();
			int cur_dist = cur_arr[0];
			int cur_node = cur_arr[1];
			
			if (!graph.containsKey(cur_node)) continue;
			if (visited[cur_node]) continue;
			
			visited[cur_node] = true;
			ArrayList<int[]> arraylist = graph.get(cur_node);
			for (int[] new_arr : arraylist) {
				int new_dist = new_arr[0];
				int new_node = new_arr[1];
				int[] tmp = {cur_dist + new_dist, new_node};
				
				if (distance[new_node] > cur_dist + new_dist) {
					distance[new_node] = cur_dist + new_dist;
					pq.add(tmp);
				}
			}
		}
		return distance[end];
	}
	
}