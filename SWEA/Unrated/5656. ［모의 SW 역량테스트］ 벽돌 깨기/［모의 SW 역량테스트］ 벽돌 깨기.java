import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N, W, H;
    static int[][] graph;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
        	H = Integer.parseInt(st.nextToken());
        	graph = new int[H][W];
        	ans = Integer.MAX_VALUE;
        	for (int row = 0; row < H; row++) {
        		st = new StringTokenizer(br.readLine());
        		for (int col = 0; col < W; col++) {
        			graph[row][col] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	backtracking(0, graph);
        	if (ans == Integer.MAX_VALUE) ans = 0;
        	System.out.println("#" + t + " " + ans);
        }
        
    }
    
     static void backtracking(int depth, int[][] cur_graph) {
    	 if (depth == N) {
    		 ans = Math.min(ans, count(cur_graph));
    		 return;
    	 }
    	 
    	 for (int col = 0; col < W; col++) {
    		 for(int row = 0; row < H; row++) {
    			 if (cur_graph[row][col] != 0) {
    				 int[][] new_graph = deepCopy(cur_graph);
    				 bomb(row, col, new_graph);
    				 down(new_graph);
    				 backtracking(depth + 1, new_graph);
    				 break;
    			 }
    		 }
    	 }
     }
     
     static int count(int[][] new_graph) {
    	 int total = 0;
    	 for (int row = 0; row < H; row++) {
    		 for (int col = 0; col < W; col++) {
    			 if (new_graph[row][col] != 0) total++;
    		 }
    	 }
    	 return total;
     }
     
     static void down(int[][] new_graph) {
    	 for (int col = 0; col < W; col++) {
    		 Stack<Integer> stack = new Stack<>();
    		 
    		 // stack에 담기
    		 for (int row = 0; row < H; row++) {
    			 if (new_graph[row][col] != 0) {
    				 stack.push(new_graph[row][col]);
    				 new_graph[row][col] = 0;
    			 }
    		 }
    		 
    		 // 아래부터 쌓기
    		 int pointer = H - 1;
    		 while (!stack.isEmpty()) {
    			 new_graph[pointer--][col] = stack.pop();
    		 }
    	 }
     }
     
     static int[][] deepCopy(int[][] cur_graph) {
    	 int[][] new_graph = new int[H][W];
    	 
    	 for (int row = 0; row < H; row++) {
    		 for (int col = 0; col < W; col++) {
    			 new_graph[row][col] = cur_graph[row][col];
    		 }
    	 }
    	 
    	 return new_graph;
     }
     
     static void bomb(int row, int col, int[][] new_graph) {
    	 int num = new_graph[row][col];
    	 
    	 Deque<int[]> deque = new LinkedList<>();
    	 deque.add(new int[] {row, col, num});
    	 
    	 while (!deque.isEmpty()) {
    		 int[] cur_arr = deque.pollFirst();
    		 row = cur_arr[0];
    		 col = cur_arr[1];
    		 num = cur_arr[2];
    		 
    		 // 상
        	 for (int r = row; r > Math.max(row-num, -1); r--) {
        		 if (new_graph[r][col] != 0) deque.add(new int[] {r, col, new_graph[r][col]});
        		 new_graph[r][col] = 0;   
        		 
        	 }
        	 // 하
        	 for (int r = row; r < Math.min(row + num, H); r++) {
        		 if (new_graph[r][col] != 0) deque.add(new int[] {r, col, new_graph[r][col]});
        		 new_graph[r][col] = 0;	     	 
        	 }
        	 // 좌
        	 for (int c = col; c > Math.max(col-num, -1); c--) {
        		 if (new_graph[row][c] != 0) deque.add(new int[] {row, c, new_graph[row][c]});
        		 new_graph[row][c] = 0;	  
        	 }
        	 //우
        	 for (int c = col; c < Math.min(col + num, W); c++) {
        		 if (new_graph[row][c] != 0) deque.add(new int[] {row, c, new_graph[row][c]});
        		 new_graph[row][c] = 0;
        	 }
    	 }
    		 
    	
     }
}