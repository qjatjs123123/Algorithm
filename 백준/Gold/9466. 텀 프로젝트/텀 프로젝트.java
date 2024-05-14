import java.io.*;
import java.util.*;

public class Main {
	static int T, N;
	static int[] arr;
	static HashMap<Integer, Boolean> visited;
	static int[] dp;
	static Stack<Integer> stack;
	static int[] check;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N + 1];
			dp = new int[N + 1];
			check = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				visited = new HashMap<>();
				stack = new Stack<>();
				stack.push(i);
				
				visited.put(i, true);
				if (dp[i] == 1) {
					ans += 1;
				}
				else {
					if (dp[i] == -1) continue;
					dfs(i, arr[i]);
					if (dp[i] == 0) {
						dp[i] = -1;
					}else {
						ans++;
					}
				}
				
			}
			System.out.println(N - ans);
			
		}
	}
	
	static int dfs(int start_node, int next_node) {
		if (dp[next_node] == -1 || dp[next_node] == 1) return 0;
		check[next_node]++;
		if (visited.containsKey(next_node)) {
			
			dp[next_node] = 1;

			while (stack.peek() != next_node) {
				dp[stack.pop()] = 1;
			}
			while (!stack.isEmpty()) {
				dp[stack.pop()] = -1;
			}
			return 0;
		}
		
		visited.put(next_node, true);
		stack.push(next_node);
		return dfs(start_node, arr[next_node]);
	}
}

