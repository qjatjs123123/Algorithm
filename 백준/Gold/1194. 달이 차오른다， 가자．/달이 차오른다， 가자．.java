
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] graph;
	static int startRow, startCol;
	static boolean[][][] visited;
	static int bitmask = 0;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1}
	};
	
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new char[N][M];
		visited = new boolean[65][N][M];
		
		for (int row = 0; row < N; row++) {
			String[] colList = br.readLine().split("");
			
			for (int col = 0; col < M; col++) {
				graph[row][col] = colList[col].charAt(0);
				if (graph[row][col] == '0') {
					startRow = row;
					startCol = col;
				}
			}
		}
		
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {startRow, startCol, 0, 0}); // row, col, 거리, 비트마스킹
		visited[0][startRow][startCol] = true;
		boolean flg = false;
		while(!deque.isEmpty()) {
			int[] cur_arr = deque.pollFirst();
			int cur_row = cur_arr[0];
			int cur_col = cur_arr[1];
			int cur_cnt = cur_arr[2];
			int cur_bit = cur_arr[3];
//			System.out.println(cur_row + " " + cur_col + " " + cur_cnt + " " + cur_bit);
			if (graph[cur_row][cur_col] == '1') {
				System.out.println(cur_cnt);
				flg = true;
				break;
			}
			
			for (int[] direct : direction) {
				int new_row = cur_row + direct[0];
				int new_col = cur_col + direct[1];
				int new_bit = cur_bit;
				
				// 맵 밖으로 벗어나거나, #을 만났을 때
				if (new_row < 0 || new_row == N || new_col < 0 || new_col == M ||  graph[new_row][new_col] == '#') continue;
				int new_item = graph[new_row][new_col];
				// 방문처리
				if (visited[new_bit][new_row][new_col]) continue;
				
				//문일 때
				if (new_item >= 65 && new_item <= 70) {
					int check = (new_bit & ( 1 << (new_item - 65 ) ));
					
					if (check == 0) continue;
				}
				
				//열쇠일 때
				if (new_item >= 97 && new_item <= 102) {
					// 열쇠 추가하기
					new_bit |= (1 << (new_item - 97));
				}

				visited[new_bit][new_row][new_col] = true;
				deque.add(new int[] {new_row, new_col, cur_cnt + 1, new_bit});
			}	
		}
		
		if (!flg) System.out.println(-1);
		
	}
	
}
