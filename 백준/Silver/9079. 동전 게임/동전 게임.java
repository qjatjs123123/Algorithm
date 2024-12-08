import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static char[][] graph = new char[3][3];
	static int ans = Integer.MAX_VALUE; 
	static boolean[] visited;
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());

		for (int t = 0; t < N; t++) {
			for (int row = 0; row < 3; row++) {
				st = new StringTokenizer(br.readLine());
				visited = new boolean[512];
				
				for (int col = 0; col < 3; col++) {
					graph[row][col] = st.nextToken().charAt(0);
				}
			}
			bfs(getBit());
		}
		
//		System.out.println(13 & ~(1 << 2));
	}
	
	static void bfs(int bit) {
		Deque<int[]> deque = new LinkedList<>();
		boolean flg = false;
		deque.add(new int[] {bit, 0});
		visited[bit] = true;
		
		while (!deque.isEmpty()) {
			int[] cur_arr = deque.pollFirst();
			int cur_bit = cur_arr[0];
			int cur_cnt = cur_arr[1];
			
			if (cur_bit == 0 || cur_bit == 511) {
				System.out.println(cur_cnt);
				flg = true;
				break;
			}

			for (int new_bit : reverse_row(cur_bit))
				deque.add(new int[] {new_bit, cur_cnt + 1});
			
			
			for (int new_bit : reverse_col(cur_bit)) 
				deque.add(new int[] {new_bit, cur_cnt + 1});

			
			for (int new_bit : reverse_cross_1(cur_bit)) 
				deque.add(new int[] {new_bit, cur_cnt + 1});
			
			
			for (int new_bit : reverse_cross_2(cur_bit)) 
				deque.add(new int[] {new_bit, cur_cnt + 1});
			
		}
		if (!flg) System.out.println(-1);
	}
	
	static ArrayList<Integer> reverse_row(int cur_bit) {
		// row
		ArrayList<Integer> list = new ArrayList<>(); 
		int[] par = new int[] {8, 7, 6}; 
		for (int col = 0; col < 3; col++) {
			int new_bit = cur_bit;
			int new_par = par[col];
					
			for (int row = 0; row < 3; row++) {
				if ((cur_bit & (1 << new_par)) == 0) new_bit |= (1 << new_par);
				else new_bit &= ~(1 << new_par);
				
				new_par -= 3;
			}

			if (visited[new_bit]) continue;
			visited[new_bit] = true;
			list.add(new_bit);
		}
		
		return list;
	}
	
	static ArrayList<Integer> reverse_cross_1(int cur_bit) {
		ArrayList<Integer> list = new ArrayList<>(); 
		int[] par = new int[] {8, 4, 0};
		int new_bit = cur_bit;
		
		for (int row = 0; row < 3; row++) {
			int new_par = par[row];
			if ((cur_bit & (1 << new_par)) == 0) new_bit |= (1 << new_par);
			else new_bit &= ~(1 << new_par);
		}
		if (visited[new_bit]) return list;
		else {
			visited[new_bit] = true;
			list.add(new_bit);
			return list;
		}
	}
	
	static ArrayList<Integer> reverse_cross_2(int cur_bit) {
		ArrayList<Integer> list = new ArrayList<>(); 
		int new_bit = cur_bit;

		if ((cur_bit & (1 << 6)) == 0) new_bit |= (1 << 6);
		else new_bit &= ~(1 << 6);
		
		if ((cur_bit & (1 << 4)) == 0) new_bit |= (1 << 4);
		else new_bit &= ~(1 << 4);
		
		if ((cur_bit & (1 << 2)) == 0) new_bit |= (1 << 2);
		else new_bit &= ~(1 << 2);
		
		if (visited[new_bit]) return list;
		else {
			visited[new_bit] = true;
			list.add(new_bit);
			return list;
		}
	}
	
	static ArrayList<Integer> reverse_col(int cur_bit) {
		// col
		ArrayList<Integer> list = new ArrayList<>(); 
		int[] par = new int[] {8, 5, 2}; 
		for (int row = 0; row < 3; row++) {
			int new_bit = cur_bit;
			int new_par = par[row];
					
			for (int col = 0; col < 3; col++) {
				if ((cur_bit & (1 << new_par)) == 0) new_bit |= (1 << new_par);
				else new_bit &= ~(1 << new_par);
				
				new_par--;
			}
			
			if (visited[new_bit]) continue;
			visited[new_bit] = true;
			list.add(new_bit);
		}
		
		return list;
	}
	
	static int getBit() {
		//HTT HTT THH  T => 1
		int par = 8;
		int bitmask = 0;
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (graph[row][col] == 'T') bitmask += Math.pow(2, par);
				par--;
			}
		}
		
		return bitmask;
	}
}
