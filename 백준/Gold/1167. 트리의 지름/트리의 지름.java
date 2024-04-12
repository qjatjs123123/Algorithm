
import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static HashMap<Integer, ArrayList<int[]>> dict = new HashMap<>();
	static boolean[] visited;
	static int max_dist = 0;
	static int max_node = 0;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				
				if (to == -1) break;
				
				int dist = Integer.parseInt(st.nextToken());
				if (dict.containsKey(from)) {
					ArrayList<int[]> tmp = dict.get(from);
					tmp.add(new int[] {to, dist});
					dict.put(from, tmp);
				} else {
					ArrayList<int[]> tmp = new ArrayList<>();
					tmp.add(new int[] {to, dist});
					dict.put(from, tmp);
				}
				
				
			}
			
		}
		visited = new boolean[T + 1];
		dfs(1, 0);
		visited = new boolean[T + 1];
		max_dist = 0;
		dfs(max_node, 0);
		System.out.println(max_dist);
	}
	
	static void dfs(int cur_node, int cur_dist) {
		
		if (!dict.containsKey(cur_node)) return;
		ArrayList<int[]> list = dict.get(cur_node);
		boolean flg = false;
		visited[cur_node] = true;
		
		for (int[] cur_list : list) {
			int new_node = cur_list[0];
			int dist = cur_list[1];
			
			if (visited[new_node]) continue;
			
			dfs(new_node, cur_dist + dist);
			flg = true;
		}

		if (!flg) {
			if (max_dist < cur_dist) {
				max_dist = cur_dist;
				max_node = cur_node;
				return;
			}
		}
	}
	
}
