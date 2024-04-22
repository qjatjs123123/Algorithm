
import java.io.*;
import java.util.*;


public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N = 0;
	static char[][] graph;
	static ArrayList<int[]> BList = new ArrayList<>();
	static ArrayList<int[]> EList = new ArrayList<>();
	static boolean[][][] visited;
	static int[][] direction = {
			{1, 0},
			{-1, 0},
			{0, -1},
			{0, 1}
	};
	static int ans = Integer.MAX_VALUE;
	static Info goal;
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		graph = new char[N][N];
		Deque<Info> deque = new LinkedList<>();
		visited = new boolean[2][N][N];
		
		for (int row = 0; row < N; row++) {
			String[] colList = br.readLine().split("");
			for (int col = 0; col < N; col++) {
				graph[row][col] = colList[col].charAt(0);
				
				if (graph[row][col] == 'B') BList.add(new int[] {row, col});
				if (graph[row][col] == 'E') EList.add(new int[] {row, col});
			}
		}
		
		int isVertical = 0;
		
		if (BList.get(0)[1] == BList.get(1)[1] && BList.get(1)[1] == BList.get(2)[1]) isVertical = 1;
		
		deque.add(new Info(BList.get(1)[0], BList.get(1)[1], 0, isVertical));
		
		if (EList.get(0)[1] == EList.get(1)[1] && EList.get(1)[1] == EList.get(2)[1]) isVertical = 1;
		else isVertical = 0;
		
		goal = new Info(EList.get(1)[0], EList.get(1)[1], 0, isVertical);
		boolean flg = false;
		while (!deque.isEmpty()) {
			Info info = deque.pollFirst();
			
			if (info.mid_row == goal.mid_row && info.mid_col == goal.mid_col && info.isVertical == goal.isVertical) {
				System.out.println(info.dist);
				flg = true;
				break;
			}
			
			// 이동하기
			for (int[] direct : direction) {
				
				int new_row = info.mid_row + direct[0];
				int new_col = info.mid_col + direct[1];
				int new_dist = info.dist + 1;
				Info new_info = new Info(new_row, new_col, new_dist, info.isVertical);
				
				if (!isMoveValid(new_info)) continue;

				visited[info.isVertical][new_row][new_col] = true;
				deque.add(new Info(new_row, new_col, new_dist, info.isVertical));
			}
			
			// 회전하기
			if (isRotateValid(info)) {
				deque.add(new Info(info.mid_row, info.mid_col, info.dist + 1, (info.isVertical + 1) % 2));
				visited[(info.isVertical + 1) % 2][info.mid_row][info.mid_col] = true;
			}
		}
		
		if (!flg) System.out.println(0);
	}
	
	static boolean isRotateValid(Info info) {
		int[][] trainArr = getTrainArr(info);
		
		if (info.mid_row == 0 || info.mid_row == N - 1 || info.mid_col == 0 || info.mid_col == N - 1) return false;
		if (visited[(info.isVertical + 1) % 2][info.mid_row][info.mid_col]) return false;
		
		int row = info.mid_row - 1;
		int col = info.mid_col - 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (graph[row + i][col + j] == '1') return false;
			}
		}
		return true;
	}
	
	static boolean isMoveValid(Info info) {
		int[][] trainArr = getTrainArr(info);

		
		
		for (int[] pos : trainArr) {
			int row = pos[0];
			int col = pos[1];
			
			if (row < 0 || row >= N || col < 0 || col >= N ) return false;
			if (graph[row][col] == '1') return false;
		}
		if (visited[info.isVertical][info.mid_row][info.mid_col]) return false;
		return true;
	}
	
	static int[][] getTrainArr(Info info) {
		int[][] new_arr = new int[3][2];
		
		if (info.isVertical == 1) {
			int[][] tmp = { {-1, 0}, {0, 0}, {1, 0} };
			
			for (int i = 0; i < 3; i++) {
				new_arr[i][0] = info.mid_row + tmp[i][0];
				new_arr[i][1] = info.mid_col + tmp[i][1];
			}
		} else {
			int[][] tmp = { {0, -1}, {0, 0}, {0, 1} };
			
			for (int i = 0; i < 3; i++) {
				new_arr[i][0] = info.mid_row + tmp[i][0];
				new_arr[i][1] = info.mid_col + tmp[i][1];
			}
		}
		return new_arr;
	}
}

class Info {
	int mid_row, mid_col, dist;
	int isVertical;

	public Info(int mid_row, int mid_col, int dist, int isVertical) {

		this.mid_row = mid_row;
		this.mid_col = mid_col;
		this.dist = dist;
		this.isVertical = isVertical;
	}
	
	@Override
	public String toString() {
		return "info [mid_row=" + mid_row + ", mid_col=" + mid_col + ", dist=" + dist + ", isVertical=" + isVertical
				+ "]";
	}


}
