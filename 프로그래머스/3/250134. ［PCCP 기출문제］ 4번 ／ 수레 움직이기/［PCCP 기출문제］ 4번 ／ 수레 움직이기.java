import java.util.*;


class Solution {
    static int direction[][] = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    static boolean[][] red_visited;
    static boolean[][] blue_visited;
    static int MAZE_ROW = 0;
    static int MAZE_COL = 0;
    static int[][] MAZE;
    static Pos red_pos;
    static Pos blue_pos;
    static int answer = Integer.MAX_VALUE;
    // [[1, 0, 2], [0, 0, 0], [5, 0, 5], [4, 0, 3]]
    public int solution(int[][] maze) {
        MAZE_ROW = maze.length;
        MAZE_COL = maze[0].length;
        
        red_visited = new boolean[MAZE_ROW][MAZE_COL];
        blue_visited = new boolean[MAZE_ROW][MAZE_COL];
        
        red_pos = null;
        blue_pos = null;
        
        MAZE = maze;
        
        for (int row = 0; row < MAZE_ROW; row++) {
            for (int col = 0; col < MAZE_COL; col++) {
                if (maze[row][col] == 1) {
                    red_visited[row][col] = true;
                    red_pos = new Pos(row, col);
                }
                
                if (maze[row][col] == 2) {
                    blue_visited[row][col] = true;
                    blue_pos = new Pos(row, col);
                }
            }
        }
        
        backtracking(0);
        if (answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
    
    static void backtracking(int depth) {
    	ArrayList<Pos[]> list;
    	//끝난다면
    	if (isAllFinish()) {
    		answer = Math.min(answer, depth);
    		return;
    	}
    	// 경로 찾기
    	if (isRedFinish()) list = getPos(true, blue_pos);
    	else if(isBlueFinish()) list = getPos(false, red_pos);
    	else list = getAllPos();

       for (Pos[] posArr : list) {
    	   if (!isMove(posArr[0], posArr[1])) continue;
    	   Pos origin_redPos = new Pos(red_pos.row, red_pos.col);
    	   Pos origin_bluePos = new Pos(blue_pos.row, blue_pos.col);
    	   
    	   red_visited[posArr[0].row][posArr[0].col] = true;
    	   blue_visited[posArr[1].row][posArr[1].col] = true;
    	   
    	   
    	   red_pos = posArr[0];
    	   blue_pos = posArr[1];
    	   
    	   
    		  
    	   backtracking(depth + 1);
    	   
    	   red_visited[posArr[0].row][posArr[0].col] = false;
    	   blue_visited[posArr[1].row][posArr[1].col] = false;
    	   
    	   red_pos = origin_redPos;
    	   blue_pos = origin_bluePos;
       }
        
    }
    
    static boolean isMove(Pos red, Pos blue) {
    	// MAZE 범위 벗어난 경우
    	if(red.row < 0 || red.row >= MAZE_ROW || blue.row < 0 || blue.row >= MAZE_ROW) return false;
    	if(red.col < 0 || red.col >= MAZE_COL || blue.col < 0 || blue.col >= MAZE_COL) return false;
    	// 빨간색, 파란색 벽 만날 경우
    	if (MAZE[red.row][red.col] == 5 || MAZE[blue.row][blue.col] == 5) return false;
    	// 빨간색, 파란색 만날 경우
    	if (red.row == blue.row && red.col == blue.col) return false;
    	// 방문처리
    	if (red_visited[red.row][red.col] && !isRedFinish()) return false;
    	if (blue_visited[blue.row][blue.col] && !isBlueFinish()) return false;
    	
    	return true;
  
    }
    
    static boolean isBlueFinish() {
    	if (MAZE[blue_pos.row][blue_pos.col] == 4) return true;
    	return false;
    }
    
    static boolean isRedFinish() {
    	if (MAZE[red_pos.row][red_pos.col] == 3) return true;
    	return false;
    }
    
    static boolean isAllFinish() {
    	if (MAZE[red_pos.row][red_pos.col] == 3 && MAZE[blue_pos.row][blue_pos.col] == 4) return true;
    	return false;
    }
    
    static ArrayList<Pos[]> getPos(Boolean isRed, Pos pos) {
    	ArrayList<Pos[]> list = new ArrayList<>();
    	
    	for (int[] direct : direction) {
    		int new_row = pos.row + direct[0];
    		int new_col = pos.col + direct[1];
    		
    		if (isRed) {
    			Pos[] tmp = {new Pos(red_pos.row, red_pos.col), new Pos(new_row, new_col)};
    			list.add(tmp);
    		} else {
    			Pos[] tmp = {new Pos(new_row, new_col), new Pos(blue_pos.row, blue_pos.col)};
    			list.add(tmp);
    		}
    	}
    	
    	return list;
    }
    
    static ArrayList<Pos[]> getAllPos() {
    	ArrayList<Pos[]> list = new ArrayList<>();
    	
    	for (int[] direct1 : direction) {
    		int new_blue_row = blue_pos.row + direct1[0];
    		int new_blue_col = blue_pos.col + direct1[1];
    		
    		for (int[] direct2 : direction) {
    			if (new_blue_row == red_pos.row && new_blue_col == red_pos.col) continue;
    			int new_red_row = red_pos.row + direct2[0];
    			int new_red_col = red_pos.col + direct2[1];
    			
    			Pos[] tmp = {new Pos(new_red_row, new_red_col), new Pos(new_blue_row, new_blue_col)};
    			list.add(tmp);
    		}
    	}
    	
    	for (int[] direct1 : direction) {
    		int new_red_row = red_pos.row + direct1[0];
    		int new_red_col = red_pos.col + direct1[1];
    		
    		for (int[] direct2 : direction) {
    			if (new_red_row == blue_pos.row && new_red_col == blue_pos.col) continue;
    			int new_blue_row = blue_pos.row + direct2[0];
    			int new_blue_col = blue_pos.col + direct2[1];
    			
    			Pos[] tmp = {new Pos(new_red_row, new_red_col), new Pos(new_blue_row, new_blue_col)};
    			list.add(tmp);
    		}
    	}
    	return list;
    }
    
    static class Pos {
        @Override
		public String toString() {
			return "Pos [row=" + row + ", col=" + col + "]";
		}

		int row, col;
        
        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}