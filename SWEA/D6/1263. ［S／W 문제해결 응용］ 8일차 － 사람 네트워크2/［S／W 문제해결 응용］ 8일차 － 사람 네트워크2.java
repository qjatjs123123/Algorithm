
import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int[][] graph;
    static int[][] path;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	graph = new int[n][n];
        	path = new int[n][n];
        	
        	for (int row = 0; row < n; row++) {
        		for (int col = 0; col < n; col++) {
        			graph[row][col] = Integer.parseInt(st.nextToken());
        			if (row == col) path[row][col] = 0;
        			else if (graph[row][col] == 0) {
        				path[row][col] = 999999;
        			} else {
        				path[row][col] = 1;
        			}
        			
        		}
        	}
			floyd(n);
			int ans = Integer.MAX_VALUE;
			
			for (int row = 0; row < n; row++) {
				int total = 0;
				
				for (int col = 0; col < n; col++) {
					total += path[row][col];
				}
				
				ans = Math.min(ans, total);
			}
			
			System.out.println("#" + t + " " + ans);
        }
    }  
    
    static void floyd(int n) {
    	for (int mid = 0; mid < n; mid++) {
    		for (int from = 0; from < n; from++) {
    			if (mid == from) continue;
    			for (int to = 0; to < n; to++) {
    				if (mid == to) continue;
    				
    				path[from][to] = Math.min(path[from][to], path[from][mid] + path[mid][to]);
    			}
    		}
    	}
    }
}