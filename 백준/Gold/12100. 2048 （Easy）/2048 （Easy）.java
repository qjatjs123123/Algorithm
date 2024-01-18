import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	static int n = 0;
	static int[][] graph;
	static int ans = 0;
	static char[] direction = {'U', 'D', 'R', 'L'};
	
	public static void main(String[] args) throws Exception {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		for (int row = 0; row < n; row++) {
			int[] colList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			for (int col = 0; col < n; col++) {
				graph[row][col] = colList[col];
			}
		}
		
		dfs(0, graph);
		System.out.println(ans);
	}
	
	static void dfs(int cnt, int[][] graph) {
		if (cnt >= 5) {
			for (int row = 0; row< n; row++) {
				for (int col = 0; col < n; col++) {
					ans = Math.max(ans, graph[row][col]);
				}
			}

			return;
		}

		for (char direct : direction) {
			int[][] new_graph = new int[n][n];
			
			for(int row = 0; row< n; row++) {
				for(int col = 0; col< n; col++) {
					new_graph[row][col] = graph[row][col];
				}
			}

			if (direct == 'U') {
				for (int col = 0; col < n; col++) {
					Deque<Integer> deque = new LinkedList<>();
					
					boolean flg = false;
					for (int row = n-1; row >= 0; row--) {
						if (graph[row][col] == 0) continue;
						
						if (deque.isEmpty() || flg) {
							deque.addLast(graph[row][col]);
							flg = false;
						} else {
							int top = deque.pollLast();
							
							if (top == graph[row][col]) {
								flg = true;
								deque.addLast(top * 2);
							}else {
								deque.addLast(top);
								deque.addLast(graph[row][col]);
							}
						}

					}
					for (int row = n - 1; row >= 0; row--) {
						if (deque.isEmpty()) new_graph[row][col] = 0;
						else new_graph[row][col] = deque.pollFirst();
					}
					
				}	//top
			} else if (direct == 'D') {
				for (int col = 0; col < n; col++) {
					Deque<Integer> deque = new LinkedList<>();
					boolean flg = false;
					for (int row = 0; row < n; row++) {
						if (graph[row][col] == 0) continue;
						
						if (deque.isEmpty() || flg) {
							deque.addLast(graph[row][col]);
							flg = false;
						} else {
							int top = deque.pollLast();
							if (top == graph[row][col]) {
								deque.addLast(top * 2);
								flg = true;
							}else {
								deque.addLast(top);
								deque.addLast(graph[row][col]);
							}
						}
					}
					for (int row = 0; row < n; row++) {
						if (deque.isEmpty()) new_graph[row][col] = 0;
						else new_graph[row][col] = deque.pollFirst();
					}
					
				}
			} else if(direct == 'R') {
				for (int row = 0; row<n; row++) {
					Deque<Integer> deque = new LinkedList<>();
					boolean flg = false;
					for(int col = n-1; col >= 0; col--) {
						if (graph[row][col] == 0) continue;
						
						if (deque.isEmpty() || flg) {
							deque.addLast(graph[row][col]);
							flg = false;
						} else {
							int top = deque.pollLast();
							if (top == graph[row][col]) {
								deque.addLast(top * 2);
								flg = true;
							}else {
								deque.addLast(top);
								deque.addLast(graph[row][col]);
							}
						}
					}
					for (int col = n-1; col >= 0; col--) {
						if (deque.isEmpty()) new_graph[row][col] = 0;
						else new_graph[row][col] = deque.pollFirst();
					}		
				}
			} else {
				for (int row = 0; row<n; row++) {
					Deque<Integer> deque = new LinkedList<>();
					boolean flg = false;
					for(int col = 0; col < n; col++) {
						if (graph[row][col] == 0) continue;
						
						if (deque.isEmpty() || flg) {
							deque.addLast(graph[row][col]);
							flg = false;
						} else {
							int top = deque.pollLast();
							if (top == graph[row][col]) {
								deque.addLast(top * 2);
								flg = true;
							}else {
								deque.addLast(top);
								deque.addLast(graph[row][col]);
							}
						}
					}
					for (int col = 0; col < n; col++) {
						if (deque.isEmpty()) new_graph[row][col] = 0;
						else new_graph[row][col] = deque.pollFirst();
					}		
				}
			}
			
			dfs(cnt + 1, new_graph);
		}
		
	}
}