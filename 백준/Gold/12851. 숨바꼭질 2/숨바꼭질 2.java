
import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] dp;
	static int[] count;
	static int ans = 0;
	static int MAX_NUM = 0;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[110000];
		visited = new boolean[110000];
		for (int i = 0; i < dp.length; i++) dp[i] = Integer.MAX_VALUE;
		dp[N] = 0; 
		count = new int[110000];
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {N, 0});
		count[N] = 1;
		while (!deque.isEmpty()) {
			int[] arr = deque.pollFirst();
			int node = arr[0];
			int dist = arr[1];
			
			if (node + 1 < dp.length && dp[node + 1] >= dist + 1) {
					count[node + 1] += count[node];
					if (dp[node + 1] != dist + 1) {
						dp[node + 1] = dist + 1;
						deque.addLast(new int[] {node + 1, dist + 1});
					}
				
				
			}
			if (node - 1 >= 0 && dp[node - 1] >= dist + 1) {
				count[node - 1] += count[node];
				if (dp[node - 1] != dist + 1) {
					dp[node - 1] = dist + 1;
					deque.addLast(new int[] {node - 1, dist + 1});
				}
				
	
			}
			if (node * 2 < dp.length && dp[node * 2] >= dist + 1) {
				count[node * 2] += count[node];
				if (dp[node * 2] != dist + 1) {
					dp[node * 2] = dist + 1;
					deque.addLast(new int[] {node * 2, dist + 1});
				}
			}
			
		}

		MAX_NUM = dp[K];
		System.out.println(MAX_NUM);
//		for (int i = 0; i < 100; i++) System.out.print(count[i] + " ");
		System.out.println(count[K]);
	}
	
	static void backtracking (int node, int cnt) {
		if (node == K && cnt == MAX_NUM) {
			ans++;
			return;
		}
		if (node < 0 || node >= dp.length) return;
		if (cnt > MAX_NUM) {
			return;
		}
		
		
		backtracking(node + 1, cnt + 1);
		backtracking(node - 1, cnt + 1);
		backtracking(node * 2, cnt + 1);
		
	}
}

