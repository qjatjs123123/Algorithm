import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int cityCnt = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int busCnt = Integer.parseInt(st.nextToken());
		
		HashMap<Integer, ArrayList<Pos>> graph = new HashMap<>();
		for (int i = 0; i < busCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if (!graph.containsKey(start)) {
				ArrayList<Pos> tmp = new ArrayList<>();
				tmp.add(new Pos(end, dist));
				graph.put(start, tmp);
			} else {
				ArrayList<Pos> tmp = graph.get(start);
				tmp.add(new Pos(end, dist));
				graph.put(start, tmp);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[cityCnt + 1];
		int[] track = new int[cityCnt + 1];

		
		
		for (int i = 0; i <= cityCnt; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[start] = 0;
		
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		Pos init_pos = new Pos(start, 0);

		pq.add(init_pos);
		
		while (!pq.isEmpty()) {
			Pos cur_pos = pq.poll();
			int cur_node = cur_pos.node;
			int cur_dist = cur_pos.dist;

			
			if (distance[cur_node] < cur_dist) continue;
			if (!graph.containsKey(cur_node)) continue;
			
			ArrayList<Pos> new_list = graph.get(cur_node);

			for (Pos pos: new_list) {
				if (distance[pos.node] > cur_dist + pos.dist) {
					
					distance[pos.node] = cur_dist + pos.dist;
					
					ArrayList<Integer> new_track = new ArrayList<>();
					
					Pos new_pos = new Pos(pos.node, cur_dist + pos.dist);

					pq.add(new_pos);
					track[pos.node] = cur_node;
					
				}
			}
		}
		
		
		System.out.println(distance[end]);

		
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		int cur_node = end;
		int cnt = 1;
		while (cur_node != start) {
			stack.push(cur_node);
			cnt++;
			cur_node = track[cur_node];
		}
		stack.push(start);
		
		while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	
	static class Pos implements Comparable<Pos>{
		int node, dist;
		ArrayList<Integer> track = new ArrayList<>();
		
		Pos(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Pos o) {
			return this.dist - o.dist; 
		}
	}
}