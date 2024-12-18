import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] graph;
	static ArrayList<int[]> cur_block;
	static int score;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
    	StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new int[10][10];
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int t = Integer.parseInt(st.nextToken());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	initBlock(t, x, y);
        	moveDown(t);
        	moveRight(t);
        	
        	removeCol();
        	removeRow();
        	removeColBonus();
        	removeRowBonus();

        }
    	System.out.println(score);
    	System.out.println(cal());
    }
    
    static int cal() {
    	int ans = 0;
    	
    	for (int row = 0; row < 4; row++) {
    		for (int col = 6; col < 10; col++) {
    			if (graph[row][col] == 1) ans++;
    		}
    	}
    	
    	for (int row = 6; row < 10; row++) {
    		for (int col = 0; col < 4; col++) {
    			if (graph[row][col] == 1) ans++;
    		}
    	}
    	
    	return ans;
    }
    
    static void removeRowBonus() {
    	int cnt = 0;
    	
    	for (int row = 0 ; row < 2; row++) {
    		int new_row = row + 4;
    		
    		for (int col = 0; col < 4; col++) {
    			if (graph[new_row][col] == 1) {
    				cnt++;
    				break;
    			}
    		}
    	}
  
 
    	if (cnt > 0) {
			// 삭제
        	for (int i = 0; i < cnt; i++) {		
        		for (int col = 0; col < 4; col++) {
        			graph[9 - i][col] = 0;
        		}
        	}
        	
        	for (int new_row = 9; new_row > 3; new_row--) {
				// 삭제
				for (int col = 0; col < 4; col++) {
    				graph[new_row][col] = 0;
    			}
				
				//땡기기
				for (int col = 0; col < 4; col++) {
    				graph[new_row][col] = graph[new_row - cnt][col];
    			}
			}
			
		}
    	
    }
    
    static void removeColBonus() {
    	int cnt = 0;
    	
    	for (int col = 0 ; col < 2; col++) {
    		int new_col = col + 4;
    		
    		for (int row = 0; row < 4; row++) {
    			if (graph[row][new_col] == 1) {
    				cnt++;
    				break;
    			}
    		}
    	}

    	if (cnt > 0) {
    		// 삭제
        	for (int i = 0; i < cnt; i++) {		
        		for (int row = 0; row < 4; row++) {
        			graph[row][9 - i] = 0;
        		}
        	}
        	
        	for (int new_col = 9; new_col > 3; new_col--) {
				// 삭제
				for (int row = 0; row < 4; row++) {
    				graph[row][new_col] = 0;
    			}
				
				//땡기기
				for (int row = 0; row < 4; row++) {
    				graph[row][new_col] = graph[row][new_col - cnt];
    			}
			}
    	}
    	
    	
    }
    
    static void removeCol() {
    	int cur_row = 9;
    	
    	while (cur_row >= 6) {
    		int cnt = 0;
    		
    		// 4개 체크
    		for (int col = 0; col < 4; col++) {
    			if (graph[cur_row][col] == 1) cnt++;
    		}
    		
    		// 4개면 삭제 후 떙기기
    		if (cnt == 4) {
    			score += 1;
    			for (int new_row = cur_row; new_row > 0; new_row--) {
    				// 삭제
    				for (int col = 0; col < 4; col++) {
        				graph[new_row][col] = 0;
        			}
    				
    				//땡기기
    				for (int col = 0; col < 4; col++) {
        				graph[new_row][col] = graph[new_row - 1][col];
        			}
    			}
    			
    			continue;
    		}
    		cur_row--;
    	}
    }
    
    static void removeRow() {
    	int cur_col = 9;
    	
    	while (cur_col >= 6) {
    		int cnt = 0;
    		
    		// 4개 체크
    		for (int row = 0; row < 4; row++) {
    			if (graph[row][cur_col] == 1) cnt++;
    		}
    		
    		// 4개면 삭제 후 떙기기
    		if (cnt == 4) {
    			score += 1;
    			for (int new_col = cur_col; new_col > 0; new_col--) {
    				// 삭제
    				for (int row = 0; row < 4; row++) {
        				graph[row][new_col] = 0;
        			}
    				
    				//땡기기
    				for (int row = 0; row < 4; row++) {
        				graph[row][new_col] = graph[row][new_col - 1];
        			}
    			}
    			
    			continue;
    		}
    		
    		cur_col--;
    	}
    	

    }
    
    static void display() {
    	for (int row = 0; row < 4; row++) {
    		 for (int col = 0; col < 10; col++) {
    			 System.out.print(graph[row][col] + " ");
    		 }
    		 System.out.println();
    	}
    	
    	for (int row = 4; row < 10; row++) {
    		for (int col = 0; col < 4; col++) {
    			System.out.print(graph[row][col] + " ");
    		}
    		System.out.println();
    	}
    }
    
    static void moveRight(int t) {
    	int width = 9;
    	
    	for (int[] cur_pos : cur_block) {
    		int cur_row = cur_pos[0];
    		int cur_col = cur_pos[1];
    		int cur_width = 9;
    		
    		for (int col = cur_col + 1; col < 10; col++) {
    			if (graph[cur_row][col] != 0) {
    				width = Math.min(col - 1, width);
    			}
    		}
    	}
    	
    	if (t == 2) {
    		int[] tmp = cur_block.get(0);
    		int cur_row = tmp[0];
    		
    		graph[cur_row][width] = 1;
    		graph[cur_row][width - 1] = 1;
    	} else {
    		for (int[] cur_pos : cur_block) {
    			int cur_row = cur_pos[0];
    			graph[cur_row][width] = 1;
    		}
    	}
    	
    }
    
    
    static void moveDown(int t) {
    	int height = 9;
    	
    	for (int[] cur_pos : cur_block) {
    		int cur_row = cur_pos[0];
    		int cur_col = cur_pos[1];
    		
    		for (int row = cur_row + 1; row < 10; row++) {
    			if (graph[row][cur_col] != 0) {
    				height = Math.min(row-1, height);
    			}
    		}
    	}
    	
    	if (t == 3) {
    		int[] tmp = cur_block.get(0);
    		int cur_col = tmp[1];
    		
    		graph[height][cur_col] = 1;
    		graph[height - 1][cur_col] = 1;
    	} else {
    		for (int[] cur_pos : cur_block) {
    			int cur_col = cur_pos[1];
    			graph[height][cur_col] = 1;
    		}
    	}
    }
    
    static void initBlock(int t, int x, int y) {
    	cur_block = new ArrayList<>();
    	
    	if (t == 1) {
    		cur_block.add(new int[] {x, y});
    	} else if (t == 2) {
    		cur_block.add(new int[] {x, y});
    		cur_block.add(new int[] {x, y + 1});
    	} else {
    		cur_block.add(new int[] {x, y});
    		cur_block.add(new int[] {x + 1, y});
    	}
    }
}
