import java.io.*;
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int n, m;
	static HashMap<Integer, ArrayList<Integer>> graph;
	static boolean[] visited;
	static boolean isCicle;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			graph = new HashMap<>();
			visited = new boolean[n + 1];
			if (n == 0 && m == 0) break;
//			System.out.println(n + " " + m);
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (graph.containsKey(a)) {
					graph.get(a).add(b);
				}else {
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(b);
					graph.put(a, tmp);
				}
				
				if (graph.containsKey(b)) {
					graph.get(b).add(a);
				}else {
					ArrayList<Integer> tmp = new ArrayList<>();
					tmp.add(a);
					graph.put(b, tmp);
				}
				
			}
			int ans = 0;

			for (int i = 1; i <= n; i++) {
				if (visited[i]) continue;
				isCicle = false;
				bfs(i);
				if (!isCicle) ans += 1;
			}
			
			if (ans == 0) sb.append("Case " + tc+ ": No trees."  ).append("\n");
			else if(ans == 1) sb.append("Case " + tc+ ": There is one tree."  ).append("\n");
			else sb.append("Case " + tc + ": A forest of " + ans + " trees.").append("\n");
			tc++;
			
		}	
		System.out.println(sb.toString());
	}	
	
	static void bfs(int node) {
		
		Deque<Integer> deque = new LinkedList<>();
		deque.add(node);
		visited[node] = true;
		boolean[][] tmp = new boolean[n+1][n+1];
		
		while (!deque.isEmpty()) {
			int cur_node = deque.pollFirst();
			
			if (!graph.containsKey(cur_node)) continue;
			for (int new_node : graph.get(cur_node)) {
				if (tmp[cur_node][new_node]) continue;
				tmp[cur_node][new_node] = true;
				tmp[new_node][cur_node] = true;
				
				
				if (visited[new_node]) {
					isCicle= true;

				}

				visited[new_node] = true;
				deque.add(new_node);
				
			}
		}
	}
}
