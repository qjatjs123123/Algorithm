import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] graph;
	static int[][] odd;
	static int[][] even;
	static ArrayList<Pos> oddList;
	static ArrayList<Pos> evenList;
	static int[][] direction = {
			{1, 1},
			{1, -1},
			{-1, 1},
			{-1, -1}
	};
	static int MAX_ODD = 0;
	static int MAX_EVEN = 0;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		odd = new int[N][N];
		even = new int[N][N];
		oddList = new ArrayList<>();
		evenList = new ArrayList<>();
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				graph[row][col] = Integer.parseInt(st.nextToken());
				odd[row][col] = graph[row][col];
				even[row][col] = graph[row][col];
			}
		}
		
		makeArr(odd, 1);
		makeArr(even, 0);
		
		

		getList(odd, oddList);
		getList(even, evenList);
		
		backtracking(0,0,odd, oddList, true);
		backtracking(0,0,even, evenList, false);

		
		System.out.println(MAX_ODD + MAX_EVEN);
	}
	
	static void backtracking(int depth, int cnt, int[][] arr, ArrayList<Pos> list, boolean isOdd) {
		
		if (depth == list.size()) {
			if (isOdd) MAX_ODD = Math.max(MAX_ODD, cnt);
			else MAX_EVEN = Math.max(MAX_EVEN, cnt);
			return;
		}
		
		Pos cur_pos = list.get(depth);
		
		if (arr[cur_pos.row][cur_pos.col] == 0) {
			backtracking(depth + 1, cnt, arr, list, isOdd);
		}else {
			for (int i = 0; i < 2; i++) {
				if (i == 0) {
					ArrayList<Pos> tmp = calRange(cur_pos, arr);
					change(tmp, arr, 0);
					backtracking(depth + 1, cnt + 1, arr, list, isOdd);
					change(tmp, arr, 1);
				} else {
					backtracking(depth + 1, cnt , arr, list, isOdd);
				}
			}
	
		}

	}
	
	static void change(ArrayList<Pos> list, int[][] arr, int n) {
		
		for (Pos pos : list) {
			int row = pos.row;
			int col = pos.col;
			
			arr[row][col] = n;
		}
	}
	
	static ArrayList<Pos> calRange(Pos cur_pos, int[][] arr) {
		
		ArrayList<Pos> list = new ArrayList<>();
		list.add(cur_pos);
		for (int[] direct : direction) {
			int new_row = cur_pos.row;
			int new_col = cur_pos.col;

			while (true) {
				new_row += direct[0];
				new_col += direct[1];
				
				if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= N) break;
				if (arr[new_row][new_col] == 0) {
					continue;
				}
				list.add(new Pos(new_row, new_col));
			}
		}
		return list;
	}
	
	static void makeArr(int[][] arr, int oddOrEven) {
		
		for (int row = 0; row < N; row++) {
			int startCol = (row + oddOrEven) % 2;
			
			for (int col = startCol; col < N; col += 2) {
				arr[row][col] = 0;
			}
		}
	}
	
	static void getList(int[][] arr, ArrayList<Pos> list) {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (arr[row][col] == 1) list.add(new Pos(row, col));
			}
		}
	}
}

class Pos {
	int row, col;

	public Pos(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "Pos [row=" + row + ", col=" + col + "]";
	}
	
	
}

