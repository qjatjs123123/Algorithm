
import java.io.*;
import java.util.*;

public class Main {
	static int[][] graph = new int[10][10];
	static int[] num = new int[6];
	static int ans = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
    	for (int i = 0; i <= 5; i++) num[i] = 5;
    	
    	int oneCnt = 0;
    	
        for (int row = 0; row < 10; row++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int col = 0; col < 10; col++) {
        		graph[row][col] = Integer.parseInt(st.nextToken());
        		
        		if (graph[row][col] == 1) oneCnt++;
        	}
        }
        
        backtracking(oneCnt, 0, 0);
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
    
    static void backtracking(int cnt, int prev_row, int prev_col) {
    	boolean flg = false;
    	if (cnt < 0) return;
    	if (cnt == 0) {
    		int total = 0;
    		for (int n : num) total += (5 - n);
    		ans = Math.min(ans, total);
    		return;
    	}
    	
    	for (int row = 0; row < 10; row++) {
    		for (int col = 0; col < 10; col++) {
    			if (graph[row][col] == 0) continue;
    			// 1~5까지 색종이 붙힐 수 있는지 확인
    			for (int n = 1; n <= 5; n++) {
    				//개수 확인
    				if (num[n] == 0) continue;
    				
    				//정사각형인지 확인
    				if (isSquare(row, col, n)) {
    					convert(row,col,n,0);
    					num[n] -= 1;
    					backtracking(cnt - n*n, row,col);
    					num[n] += 1;
    					convert(row,col,n,1);
    					flg = true;
    				}
    				
    			}
    			if (flg) break;
    		}
    		if (flg) break;
    	}
    }
    
    static boolean isSquare(int row, int col, int n) {
    	int cnt = 0;
    	boolean flg = false;
    	for (int r = row; r < Math.min(10, row + n); r++) {
    		for (int c = col; c < Math.min(10, col + n); c++) {
    			if (graph[r][c] == 1) cnt++;
    			if (graph[r][c] == 0) {
    				flg = true;
    				break;
    			}
    		}
    		if (flg) break;
    	}
    	
    	if (cnt == n*n) return true;
    	return false;
    }
    
    static void convert(int row, int col, int n, int target) {
    	for (int r = row; r < Math.min(10, row + n); r++) {
    		for (int c = col; c < Math.min(10, col + n); c++) {
    			graph[r][c] = target;
    		}
    	}
    }
    
}