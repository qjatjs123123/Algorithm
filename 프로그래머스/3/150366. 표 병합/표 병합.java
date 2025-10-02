import java.util.*;

class Solution {
    static class Cell {
        String value = "";
        int r, c;
        int id = -1;
        
        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static class Table {
        final int TABLE_SIZE = 51;
        Cell[][] table = new Cell[TABLE_SIZE][TABLE_SIZE];
        int autoIncrease = 0;
        HashMap<Integer, ArrayList<Cell>> mergeMap = new HashMap<>();
        
        Table() {
            for (int row = 1; row < TABLE_SIZE; row++) {
                for (int col = 1; col < TABLE_SIZE; col++) table[row][col] = new Cell(row, col);
            }
        }
        
        void update(int r, int c, String value) {
            Cell cell = table[r][c];
            
            if (cell.id != -1) {
                ArrayList<Cell> list = mergeMap.get(cell.id);
                
                for (Cell tmpCell : list) tmpCell.value = value; 
            } else {
                cell.value = value;
            }
        }
        
        void update(String value1, String value2) {
            for (int row = 1; row < TABLE_SIZE; row++) {
                for (int col = 1; col < TABLE_SIZE; col++) {
                    Cell cell = table[row][col];
                    
                    if (cell.value.equals(value1)) cell.value = value2;
                }
            }
        }
        
        void merge(int r1, int c1, int r2, int c2) {
            Cell cellOne = table[r1][c1];
            Cell cellTwo = table[r2][c2];
            
            if (cellOne.id != -1 && cellOne.id == cellTwo.id) return;
            
            boolean flg1 = false;
            boolean flg2 = false;
            String newValue = cellTwo.value;
            autoIncrease++;
            
            ArrayList<Cell> newList = new ArrayList<>();
            
            if (cellOne.id != -1) {
                ArrayList<Cell> list = mergeMap.get(cellOne.id);
                
                for (Cell cell : list) newList.add(cell);
                mergeMap.remove(cellOne.id);
                flg1 = true;
                if (!cellOne.value.equals("")) newValue = cellOne.value;
            }
            
            if (cellTwo.id != -1) {
                ArrayList<Cell> list = mergeMap.get(cellTwo.id);
                
                for (Cell cell : list) newList.add(cell);
                mergeMap.remove(cellTwo.id);
                flg2 = true;
            }
            
            if (!flg1) {
                newList.add(cellOne);
                if (!cellOne.value.equals("")) newValue = cellOne.value;
            }
            if (!flg2) newList.add(cellTwo);
            
            for (Cell cell : newList) {
                cell.id = autoIncrease;
                cell.value = newValue;
            }
            
            mergeMap.put(autoIncrease, newList);
        }
        
        void unmerge(int r, int c) {
            Cell cell = table[r][c];
            
            String origin = cell.value;
            if (cell.id == -1) return;
            
            ArrayList<Cell> list = mergeMap.get(cell.id);
            for (Cell tmp : list) {
                tmp.id = -1;
                tmp.value = "";
            }
            
            cell.value = origin;
            mergeMap.remove(cell.id);
        }
        
        String print(int r, int c) {
            Cell cell = table[r][c];
            
            if (cell.value.equals("")) return "EMPTY";
            return cell.value;
        }
    }
    
    public String[] solution(String[] commands) {
        Table tb = new Table();
        Deque<String> deque = new ArrayDeque<>();
        
        for(String command : commands) {
            String[] cmdArr = command.split(" ");
            
            if (cmdArr[0].equals("UPDATE")) {
                if (cmdArr.length == 4) tb.update(Integer.parseInt(cmdArr[1]), Integer.parseInt(cmdArr[2]), cmdArr[3]);
                else tb.update(cmdArr[1], cmdArr[2]);
            } else if (cmdArr[0].equals("MERGE")) {
                int r1 = Integer.parseInt(cmdArr[1]);
                int c1 = Integer.parseInt(cmdArr[2]);
                int r2 = Integer.parseInt(cmdArr[3]);
                int c2 = Integer.parseInt(cmdArr[4]);
                
                tb.merge(r1, c1, r2, c2);
            } else if (cmdArr[0].equals("UNMERGE")) {
                int r1 = Integer.parseInt(cmdArr[1]);
                int c1 = Integer.parseInt(cmdArr[2]);
                
                tb.unmerge(r1, c1);
            } else {
                int r1 = Integer.parseInt(cmdArr[1]);
                int c1 = Integer.parseInt(cmdArr[2]);
                
                deque.addLast(tb.print(r1, c1));
            }
        }
        String[] answer = new String[deque.size()];
        int idx =  0;
        
        while (!deque.isEmpty()) answer[idx++] = deque.pollFirst();
        return answer;
    }
}