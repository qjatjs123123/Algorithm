import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[][] graph = new int[3][3];
    static int player;
    static int isWin = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int row = 0; row < 3; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 3; col++){
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++) {
                if (graph[row][col] == 1) count++;
                if (graph[row][col] == 2) count++;
            }
        }

        player = count % 2 == 0 ? 1 : 2;

        isWin = backtracking(count);
        
        if (isWin > 0) System.out.println("W");
        else if (isWin == 0) System.out.println("D");
        else System.out.println("L");
    }

    static int leftResult() {
        int cur_num = graph[0][0];

        if (cur_num == 0) return 0;

        if ((cur_num == graph[1][1]) && (cur_num == graph[2][2])) return cur_num;
        return 0;
    }

    static int rightResult() {
        int cur_num = graph[0][2];

        if (cur_num == 0) return 0;

        if ((cur_num == graph[1][1]) && (cur_num == graph[2][0])) return cur_num;
        return 0;
    }
    
    static int colResult() {
        int cur_player = 0;
        
        for (int row = 0; row < 3; row++) {
            int cur_num = graph[row][0];
            if (cur_num == 0) continue;
            
            cur_player = cur_num;
            for (int col = 1; col < 3; col++){
                if  (graph[row][col]  != cur_num) {
                    cur_player = 0;
                    break;
                }
            }

            if (cur_player != 0) break;
        }
        return cur_player;
    }
    
    static int rowResult() {
        int cur_player = 0;
        
        for (int col = 0; col < 3; col++) {
            int cur_num = graph[0][col];
            if (cur_num == 0) continue;
            
            cur_player = cur_num;
            for (int row = 1; row < 3; row++){
                if  (graph[row][col]  != cur_num) {
                    cur_player = 0;
                    break;
                }
            }

            if (cur_player != 0) break;
        }
        return cur_player;
    }
    
    static int getResult() {
        int lResult = leftResult();
        if (lResult != 0) return lResult;

        int rResult = rightResult();
        if (rResult != 0) return rResult;

        int rowRe = rowResult();
        if (rowRe != 0) return rowRe;

        int colRe = colResult();
        if(colRe != 0) return colRe;

        return 0;
    }

    
    static int backtracking(int count) {
        int cur_node = count % 2 == 0 ? 1 : 2;
        int cur_result = getResult();
        
        if (cur_result != 0) {
            if (cur_result == player) return 1;
            return -1;
        }
        
        if (count == 9) {

            return 0;
        }

        int total = cur_node == player ?  -1 : 1;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (graph[row][col] != 0) continue;

                graph[row][col] = cur_node;

                if (cur_node == player) total = Math.max(total , backtracking(count + 1));
                else total = Math.min(total, backtracking(count + 1));

                
                graph[row][col] = 0;
                
            }
        }

        return total;
    }
}