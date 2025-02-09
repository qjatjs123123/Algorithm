import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] graph = new int[9][9];
    static int blank_cnt = 0;
    static HashMap<Integer, Boolean> rowDict[] = new HashMap[9];
    static HashMap<Integer, Boolean> colDict[] = new HashMap[9];
    static HashMap<Integer, Boolean> rectDict[][] = new HashMap[3][3]; 
    static ArrayList<int[]> blankList = new ArrayList<>();
    static boolean flg = false;
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int row = 0; row < 9; row++) {
            rowDict[row] = new HashMap<>();
            colDict[row] = new HashMap<>();
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) rectDict[row][col] = new HashMap<>();
        }
        
        for (int row = 0; row < 9; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int col = 0; col < 9; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());    

                if (graph[row][col] == 0) {
                    blank_cnt++;
                    blankList.add(new int[] {row, col});
                }
                else {
                    int new_row = (row / 3);
                    int new_col = (col / 3);
                    
                    rowDict[row].put(graph[row][col], true);
                    colDict[col].put(graph[row][col], true); 
                    rectDict[new_row][new_col].put(graph[row][col], true);
                }
            }
            
        }

        backtracking(0);
    }

    static void backtracking(int depth) {
        if (flg) return;
        
        if (depth == blank_cnt) {

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++ ){
                    System.out.print(graph[row][col] + " ");
                }
                System.out.println("");
            }
            flg = true;
            return;
        }
        
        int row = blankList.get(depth)[0];
        int col = blankList.get(depth)[1];
        
        for (int num = 1; num <= 9; num++) {
            if (isRowCheck(row, num) && isColCheck(col, num) && isRect(row, col, num)) {
                int new_row = (row / 3);
                int new_col = (col / 3);
                
                graph[row][col] = num;
                rowDict[row].put(num, true);
                colDict[col].put(num, true);
                rectDict[new_row][new_col].put(num, true);
                
                backtracking(depth + 1);

                graph[row][col] = 0;
                rectDict[new_row][new_col].remove(num);
                rowDict[row].remove(num);
                colDict[col].remove(num);
            }
        }

    }

    static boolean isRect(int row, int col, int num) {
        int new_row = (row / 3);
        int new_col = (col / 3);
        HashMap<Integer, Boolean> cur_rectDict = rectDict[new_row][new_col];
        
        return !cur_rectDict.containsKey(num);
    }
    
    static boolean isRowCheck(int row, int num) {
        HashMap<Integer, Boolean> cur_rowDict = rowDict[row];
        return !cur_rowDict.containsKey(num);
    }

    static boolean isColCheck(int col, int num) {
        HashMap<Integer, Boolean> cur_colDict = colDict[col];
        return !cur_colDict.containsKey(num);
    }
}