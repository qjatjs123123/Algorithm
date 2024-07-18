import java.io.*;
import java.util.*;

class Main {
	static int N, M;
	static char[][] graph;
	static boolean[][][] visited;
	static int DIRT = 0;
	static int BITMASK = 0;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	static ArrayList<Pos> dirt_list;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Pos startPos = null;
			
			if (N == 0 && M == 0)  break;

			graph = new char[M][N];
			dirt_list = new ArrayList<>();
			DIRT = 0;
			
			for (int row = 0; row < M; row++) {
				String[] strArr = br.readLine().split("");
				
				for (int col = 0; col < N; col++) {
					graph[row][col] = strArr[col].charAt(0);
					if (graph[row][col] == '*') {
						DIRT++;
						dirt_list.add(new Pos(row, col, 0, 0));
					}
					if (graph[row][col] == 'o') startPos = new Pos(row, col, 0, 0);
				}
				
			}
			
			BITMASK = (1 << DIRT) - 1;
			visited = new boolean[BITMASK + 1][M][N];

			Deque<Pos> deque = new LinkedList<>();
			deque.add(startPos);
			int ans = -1;
			while (!deque.isEmpty()) {
				Pos cur_pos = deque.pollFirst();
				int cur_bit = cur_pos.bit;
				
				if (cur_pos.bit == BITMASK) {
					ans = cur_pos.cnt;
					break;
				}
				
				for (int[] direct : direction) {
					int new_row = cur_pos.row + direct[0];
					int new_col = cur_pos.col + direct[1];
	
					if (new_row < 0 || new_row >= M || new_col < 0 || new_col >= N) continue;
					if (graph[new_row][new_col] == 'x') continue;
					if (visited[cur_bit][new_row][new_col]) continue;
					
					if (graph[new_row][new_col] == '*') {
						int bit_idx = 0;
						int idx = 0;
						for (Pos dirt_pos : dirt_list) {
							if (dirt_pos.row == new_row && dirt_pos.col == new_col) {
								bit_idx = idx;
								break;
							}
							idx++;
						}	
						
						
						int new_bit = cur_bit | (1 << bit_idx);
						if ((cur_bit & (1 << bit_idx)) > 0 ) {
							visited[cur_bit][new_row][new_col] = true;
							deque.add(new Pos(new_row, new_col, cur_pos.cnt + 1, cur_bit));
							continue;
						};
						
						deque.add(new Pos(new_row, new_col, cur_pos.cnt + 1, new_bit));
						visited[new_bit][new_row][new_col] = true;
						continue;
					}
					
					visited[cur_bit][new_row][new_col] = true;
					deque.add(new Pos(new_row, new_col, cur_pos.cnt + 1, cur_bit));
					
				}
				
			}
			
			System.out.println(ans);
		}
		
	}
	
	static class Pos {
		int row, col, cnt, bit;
		Pos(int row, int col, int cnt, int bit) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
			this.bit = bit;
		}
	}
}