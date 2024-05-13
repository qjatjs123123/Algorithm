import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int H, W;
	static char[][] graph;
	static char[][] original;
	static boolean[][] visited;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	static HashMap<Character, Boolean> keyMap;
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			graph = new char[H + 2][W + 2];
			visited = new boolean[H + 2][W + 2];
			keyMap = new HashMap<>();
			init();
			
			for (int row = 0; row < H; row++) {
				String[] colList = br.readLine().split("");
				
				for (int col = 0; col < W; col++) graph[row+1][col+1] = colList[col].charAt(0);
			}
			
			String[] keyList = br.readLine().split("");
			for (String key : keyList) keyMap.put(key.charAt(0), true);
			
			Deque<int[]> deque = new LinkedList<>();
			deque.add(new int[] {0, 0});
			visited[0][0] = true;
			
			while (!deque.isEmpty()) {
				int[] cur_arr = deque.pollFirst();
				int cur_row = cur_arr[0];
				int cur_col = cur_arr[1];
				
				for (int[] direct : direction) {
					int new_row = cur_row + direct[0];
					int new_col = cur_col + direct[1];
					
					if (new_row < 0 || new_row == H + 2 || new_col < 0 || new_col == W + 2) continue;
					if (graph[new_row][new_col] == '*') continue;
					if (visited[new_row][new_col]) continue;
					
					int num = (int)graph[new_row][new_col];
					
					//문
					if (num >= 65 && num <= 90) {
						char lower = Character.toLowerCase(graph[new_row][new_col]);
						if (!keyMap.containsKey(lower)) continue;
					}
					
					// 열쇠
					if (num >= 97 && num <= 122) {
						char lower = graph[new_row][new_col];
						if (!keyMap.containsKey(lower)) {
							keyMap.put(lower, true);
							visited = new boolean[H + 2][W + 2];
							visited[new_row][new_col] = true;
							deque.clear();
							deque.add(new int[] {new_row, new_col});
							continue;
						}
					}
					
					visited[new_row][new_col] = true;
					deque.add(new int[] {new_row, new_col});
				}
				
			}
			
			deque.clear();
			deque = new LinkedList<>();
			deque.add(new int[] {0, 0});
			visited = new boolean[H + 2][W + 2];
			visited[0][0] = true;
			int ans = 0;
			
			while (!deque.isEmpty()) {
				int[] cur_arr = deque.pollFirst();
				int cur_row = cur_arr[0];
				int cur_col = cur_arr[1];
				for (int[] direct : direction) {
					int new_row = cur_row + direct[0];
					int new_col = cur_col + direct[1];
					
					if (new_row < 0 || new_row == H + 2 || new_col < 0 || new_col == W + 2) continue;
					if (graph[new_row][new_col] == '*') continue;
					if (visited[new_row][new_col]) continue;
					
					int num = (int)graph[new_row][new_col];
					
					//문
					if (num >= 65 && num <= 90) {
						char lower = Character.toLowerCase(graph[new_row][new_col]);
						if (!keyMap.containsKey(lower)) continue;
					}
					
					if (graph[new_row][new_col] == '$') ans++;
					visited[new_row][new_col] = true;
					deque.add(new int[] {new_row, new_col});
				}
			}
			
			System.out.println(ans);
		}
		
	}
	
	
	
	static void init() {
		for (int row = 0; row < H + 2; row++) {
			for (int col = 0; col < W + 2; col++) {
				graph[row][col]= '.';
			}
		}
	}
	
	
}

