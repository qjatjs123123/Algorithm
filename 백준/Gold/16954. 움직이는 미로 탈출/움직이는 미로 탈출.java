import java.io.*;
import java.util.*;

public class Main {
	static char[][] graph = new char[8][8];
	static Deque<int[]> WALL_LIST = new LinkedList<>();
	static Deque<int[]> CHARACTER_LIST = new LinkedList<>();
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, 1},
			{0, -1},
			{1, 1},
			{1, -1},
			{-1, 1},
			{-1, -1},
			{0, 0}
	};
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		
		for (int row = 0; row < 8; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for (int col = 0; col < 8; col++) {
				graph[row][col] = line.charAt(col);
				
				if (graph[row][col] == '#') WALL_LIST.add(new int[] {row, col});
			}
		}
		CHARACTER_LIST.add(new int[] {7, 0});
		
		int ans = 0;
		for (int time = 0; time < 9; time++) {
			if (CHARACTER_LIST.isEmpty()) {
				break;
			}
			if (WALL_LIST.isEmpty() && !CHARACTER_LIST.isEmpty()) {
				ans = 1;
				break;
			}
			
			character_move();
			wall_move();
			

		}
		if (!CHARACTER_LIST.isEmpty()) ans = 1;
		System.out.println(ans);
	}
	
	static void character_move() {
		Deque<int[]> NEW_LIST = new LinkedList<>();
		while (!CHARACTER_LIST.isEmpty()) {
			int[] character_pos = CHARACTER_LIST.pollFirst();
			int character_row = character_pos[0];
			int character_col = character_pos[1];
			
			if (graph[character_row][character_col] == '#') continue;
			
			for (int[] direct : direction) {
				int new_row = character_row + direct[0];
				int new_col = character_col + direct[1];
				
				if (new_row < 0 || new_row == 8 || new_col < 0 || new_col == 8) continue;
				if (graph[new_row][new_col] == '#') continue;
				
				NEW_LIST.add(new int[] {new_row, new_col});
			}
		}
		
		CHARACTER_LIST = NEW_LIST;
	}

	
	static void wall_move() {
		Deque<int[]> NEW_LIST = new LinkedList<>();
		while (!WALL_LIST.isEmpty()) {
			int[] wall_pos = WALL_LIST.pollLast();
			int wall_row = wall_pos[0];
			int wall_col = wall_pos[1];
			
			if (wall_row == 7) {
				graph[wall_row][wall_col] = '.';
				continue;
			}
			
			graph[wall_row][wall_col] = '.';
			graph[wall_row + 1][wall_col] = '#';
			
			NEW_LIST.addFirst(new int[] {wall_row + 1, wall_col});
		}
		WALL_LIST = NEW_LIST;
	}
	
}
