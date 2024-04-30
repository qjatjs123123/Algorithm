
import java.io.*;
import java.util.*;

public class Main {
	static int T, N, K;
	static int[] TIME;
	static HashMap<Integer, ArrayList<Integer>> graph;
	static int[] dp;
	static int[] count;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			TIME = new int[N + 1];
			dp = new int[N + 1];
			count = new int[N + 1];
			graph = new HashMap<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) TIME[i] = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if (graph.containsKey(from)) {
					ArrayList<Integer> list = graph.get(from);
					list.add(to);
					graph.put(from, list);
				} else {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(to);
					graph.put(from, list);
				}
				
				count[to]++;
			}
			
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			
			Deque<Integer> deque = new LinkedList<>();
			
			// init
			for (int i = 1; i <= N; i++) {
				if (count[i] == 0) {
					deque.add(i);
					dp[i] = TIME[i];
				}
			}
			
			while (!deque.isEmpty()) {
				int cur_node = deque.pollFirst();
				// 종료 조건
				if (count[target] == 0) break;
				
				if (!graph.containsKey(cur_node)) continue;
				
				ArrayList<Integer> new_list = graph.get(cur_node);
				
				for (int new_node : new_list) {
					dp[new_node] = Math.max(dp[new_node], dp[cur_node] + TIME[new_node]);
					
					count[new_node] -= 1;
					if (count[new_node] == 0) {
						deque.add(new_node);
					}
				}
			}
			
			System.out.println(dp[target]);
		}

	}
}

