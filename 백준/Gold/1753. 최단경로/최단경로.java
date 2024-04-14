import java.io.*;
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int V, E;
	static int K;

	static int[] distance;
	static boolean[] visited;
	static ArrayList<ArrayList<int[]>> graph;
	
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E  = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		
		for (int row = 0; row < E; row++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(start).add(new int[] {cost, end});
		}
		
		
		distance = new int[V + 1];
		visited = new boolean[V + 1];
		
		for (int i = 0; i <= V; i++) distance[i] = Integer.MAX_VALUE;
		
		dijkstra();
		for (int i = 1; i<= V; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			}
			else sb.append(distance[i]).append("\n");
		}
		
		System.out.println(sb.toString());
	}	
	
	static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
		pq.add(new int[] {0, K});
		distance[K] = 0;
		
		while (!pq.isEmpty()) {
			int[] tmp = pq.poll();
			int cost = tmp[0];
			int node = tmp[1];
			
			if (distance[node] < cost || visited[node]) continue;
			visited[node] = true;
			ArrayList<int[]> list = graph.get(node);
			
			for (int[] arr : list) {
				if (visited[arr[1]]) continue;
				
				if (distance[arr[1]] > cost + arr[0]) {
					distance[arr[1]] = cost + arr[0];
					pq.add(new int[] {distance[arr[1]], arr[1]});
				}
			}
			
		}
	}
}
