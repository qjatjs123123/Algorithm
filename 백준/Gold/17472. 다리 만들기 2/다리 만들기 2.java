
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int islandCnt = 1;
    static int[][] dp;
    static ArrayList<int[]> pathList = new ArrayList<>();
    static Stack<Integer> stack = new Stack<>();
    static HashMap<Integer, int[]> map = new HashMap<>();
    static int[] parent;
    static int[][] direction = {
    		{1, 0},
    		{-1, 0},
    		{0, 1},
    		{0, -1}
    };
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];
        
        for (int row = 0; row < N; row++) {
        	st = new StringTokenizer(br.readLine());
        	
        	for (int col = 0; col < M; col++) {
        		graph[row][col] = Integer.parseInt(st.nextToken());
        	}
        }
        
        
        // 섬 카운트 및 섬 구분하기
        for (int row = 0; row < N; row++) {
        	for (int col = 0; col < M; col++) {
        		if (graph[row][col] != 0 && !visited[row][col]) {
        			decideIsland(row, col);
        			islandCnt++;
        		}
        	}
        }
        
        dp = new int[islandCnt + 1][islandCnt + 1];
        //dp 초기화
        for (int row = 0; row <= islandCnt; row++) {
        	for (int col = 0; col <= islandCnt; col++) dp[row][col] = Integer.MAX_VALUE;
        }
        
        // 최단거리 구하기
        for (int row = 0; row < N; row++) {
        	for (int col = 0; col < M; col++) {
        		if (graph[row][col] != 0 ) {
        			decidePath(row, col);
        		}
        	}
        }
        
        // 다리 길이 2이상인 모든 경로 구하기
        for (int row = 1; row <= islandCnt; row++) {
        	for (int col = row + 1; col <= islandCnt; col++) {
        		if (dp[row][col] != Integer.MAX_VALUE) {
        			pathList.add(new int[] {row, col});
        		}
        	}
        }
        
        backtracking(0, 0);
        if (result == Integer.MAX_VALUE) result= -1;
        System.out.println(result);
    }  
    
    static int[][] deepCopy() {
    	int[][] new_graph = new int[N][M];
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < M; col++) {
    			new_graph[row][col] = graph[row][col];
    		}
    	}
    	
    	return new_graph;
    }
    
    static int draw(int[][] new_graph, int[] arr) {
    	int fromRow = arr[0];
    	int fromCol = arr[1];
    	int toRow = arr[2];
    	int toCol = arr[3];
    	int dx = arr[4];
    	int dy = arr[5];
    	int total = 0;
    	int target = new_graph[fromRow][fromCol];
    	
    	Deque<int[]> deque = new LinkedList<>();
    	deque.add(new int[] {fromRow, fromCol});
    	
    	while (!deque.isEmpty()) {
    		int[] pos = deque.pollFirst();
    		
    		int new_row = pos[0];
    		int new_col = pos[1];
    		
    		if (new_row == toRow && new_col == toCol) {
    			union(target, new_graph[toRow][toCol]);
    			break;
    		}
    		if (new_graph[new_row][new_col] <= 0) {
    			new_graph[new_row][new_col] = -1;
    			total++;
    		}
    		deque.add(new int[] {new_row + dx, new_col + dy});
    	}
    	
    	return total;
    }
    
    static void makeset() {
    	parent = new int[islandCnt];
    	
    	for (int i = 1; i < islandCnt; i++) parent[i] = i;
    }
    
    static int find(int node) {
    	if (node == parent[node]) return parent[node];
    	return parent[node] = find(parent[node]);
    }
    
    static void union(int node, int new_node) {
    	int p = find(node);
    	int new_p = find(new_node);
    	parent[p] = new_p;
    }
    
    static void backtracking(int depth, int start) {
    	if (depth == islandCnt - 2) {
    		int[][] new_graph = deepCopy();
    		makeset();
    		int ans = 0;
    		for (int idx : stack) {
    			int[] pos = pathList.get(idx);
    			int hash = pos[0] * 10 + pos[1];
    			int[] tmp = map.get(hash);
    			ans += draw(new_graph, tmp);
    		}
    		boolean a = true;
    		int target = find(1);
        	for (int i = 2; i < islandCnt; i++) {
        		int t = find(i);
        		if (t != target) {
        			a = false;
        			break;
        		}
        	}

        	
        		
        	if (a) {

        		result = Math.min(result, ans);
        	}
    		
    		return;
    	}
    	
    	for (int i = start; i < pathList.size(); i++) {
    		stack.push(i);
    		backtracking(depth + 1, i + 1);
    		stack.pop();
    	}
    }
    
    static void decidePath(int row, int col) {
    	int target = graph[row][col];
    	
    	for (int[] direct : direction) {		
    		Deque<int[]> deque = new LinkedList<>();
    		deque.add(new int[] {row, col, 0});
    		
    		while (!deque.isEmpty()) {
    			int[] cur_arr = deque.pollFirst();
    			int new_row = cur_arr[0] + direct[0];
        		int new_col = cur_arr[1] + direct[1];
        		int new_cnt = cur_arr[2];
        		
        		if (new_row < 0 || new_row >= N || new_col < 0 || new_col >= M || graph[new_row][new_col] == target) break;
        		if (graph[new_row][new_col] >= 1 && graph[new_row][new_col] <= 6) {
        			if (new_cnt <= 1) break;
        			
        			if (dp[target][graph[new_row][new_col]] > new_cnt) {
        				dp[target][graph[new_row][new_col]] = Math.min(dp[target][graph[new_row][new_col]], new_cnt);
        				int hash = target * 10 + graph[new_row][new_col];
            			int[] tmp = new int[] {row, col, new_row, new_col, direct[0], direct[1]};
            			map.put(hash, tmp);
        			}
        			
        			break;
        		}
        		deque.add(new int[] {new_row, new_col , ++new_cnt});
    		}
    		
    		
    	}
    }
    
    static void decideIsland(int row, int col) {
    	Deque<int[]> deque = new LinkedList<>();
    	deque.add(new int[] {row, col});
    	graph[row][col] = islandCnt;
    	visited[row][col] = true;
    	
    	while (!deque.isEmpty()) {
    		int[] cur_arr = deque.pollFirst();
    		int cur_row = cur_arr[0];
    		int cur_col = cur_arr[1];
    		
    		for (int[] direct : direction) {
    			int new_row = cur_row + direct[0];
    			int new_col = cur_col + direct[1];
    			
    			if (new_row < 0 || new_row == N || new_col < 0 || new_col == M) continue;
    			if (graph[new_row][new_col] == 0 || visited[new_row][new_col]) continue;
    			
    			graph[new_row][new_col] = islandCnt;
    			visited[new_row][new_col] = true;
    			deque.add(new int[] {new_row, new_col});
    		}
    	}
    }
}