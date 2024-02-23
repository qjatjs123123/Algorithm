import java.io.*;
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int n;
	static ArrayList<Integer> stack;
	static boolean[] visited;
	static int[][] graph;
	static int ans = 0;
	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		graph = new int[n][9];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 9; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		stack = new ArrayList<>();
		visited = new boolean[9];
		
		backtracking(0);
		System.out.println(ans);
	}	
	
	static void backtracking(int l) {
		if (l == 8) {
			//idxList초기화
			int[] idxList = new int[9];
			for (int i = 0; i<9; i++) {
				if (i < 3) idxList[i] = stack.get(i);
				else if(i == 3) idxList[i] = 0;
				else idxList[i] = stack.get(i-1);
			}	
			int idx = -1;
			int total = 0;
			for (int[] inning : graph) {
				
				int out = 0;
				Deque<Integer> deque = new LinkedList<>();
				deque.add(0);
				deque.add(0);
				deque.add(0);
				int base1 = 0;
				int base2 = 0;
				int base3 = 0;
				while (true) {
					if (out == 3) break;
					idx = (idx + 1) % 9;
					int ballType = inning[idxList[idx]];
					
					if (ballType == 0) {
						out++;
						continue;
					}

					if (ballType == 1) {
						
						total += base3;
						base3 = base2;
						base2 = base1;
						base1 = 1;
					}
					else if(ballType == 2) {
						total += base3 + base2;
						base3 = base1;
						base2 = 1;
						base1 = 0;
					}else if (ballType == 3) {
						total += base1 + base2+ base3;
						base1 = 0;
						base2 = 0;
						base3 = 1;
					}else {
						total += base1 + base2+ base3 + 1;
						base1 = 0;
						base2 = 0;
						base3 = 0;
					}
				
				}
			}
			
			ans = Math.max(ans, total);
			
			
			return;
		}
		
		for (int i = 1; i < 9; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			stack.add(i);
			backtracking(l + 1);
			visited[i] = false;
			stack.remove(stack.size() - 1);
		}
	}
	
}
