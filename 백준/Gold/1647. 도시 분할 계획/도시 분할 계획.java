import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static Edge[] EdgeList;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		EdgeList = new Edge[M];
		parents = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			Edge edge = new Edge(from, to, dist);
			
			EdgeList[i] = edge;
		}
		
		Arrays.sort(EdgeList);
		
		make();
		int cnt = 0;
		int max_dist = 0;
		int ans = 0;
		
		
		for (Edge edge : EdgeList) {
			int cur_from = edge.from;
			int cur_to = edge.to;
			int cur_dist = edge.distance;

			if (!union(cur_from, cur_to)) continue;

			max_dist = Math.max(max_dist, cur_dist);
			ans += cur_dist;
			if (++cnt == N - 1) break; 
			
		}

		System.out.println(ans - max_dist);
	}
	
	static void make() {
		for (int i = 0; i <= N; i++) parents[i] = i;
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		parents[aRoot] = parents[bRoot];
		return true;
	}
}

class Edge implements Comparable<Edge> {
	int from, to, distance;
	
	public Edge(int from, int to, int distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", distance=" + distance + "]";
	}



	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.distance - o.distance;
	}
	
}

