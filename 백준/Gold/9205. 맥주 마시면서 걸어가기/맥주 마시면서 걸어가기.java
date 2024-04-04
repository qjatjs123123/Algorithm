
import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M;

   
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	int homeRow = Integer.parseInt(st.nextToken());
        	int homeCol = Integer.parseInt(st.nextToken());
        	
        	ArrayList<int[]> storeList = new ArrayList<>();
        	
        	for (int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		
        		int storeRow = Integer.parseInt(st.nextToken());
        		int storeCol = Integer.parseInt(st.nextToken());
        		
        		storeList.add(new int[] {storeRow, storeCol});
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	
        	int festivalRow = Integer.parseInt(st.nextToken());
        	int festivalCol = Integer.parseInt(st.nextToken());
        	
        	HashMap<Long, Boolean> visited = new HashMap<>();
        	
        	Deque<int[]> deque = new LinkedList<>();
        	
        	deque.add(new int[] {homeRow, homeCol});
        	
        	boolean flg = false;
        	
        	while (!deque.isEmpty()) {
        		int[] cur_arr = deque.pollFirst();
        		long cur_row = cur_arr[0];
        		long cur_col = cur_arr[1];
        		
        		
        		long dist = getDistance(cur_row, cur_col, festivalRow, festivalCol);
        		if (dist <= 1000) {
        			flg = true;
        			break;
        		}
        		
        		
        		visited.put(getHash(homeRow, homeCol),  true);
        		
        		for (int[] store : storeList) {
        			long hash = getHash(store[0], store[1]);
        			if (visited.containsKey(hash)) continue;
        			
        			long gap = getDistance(cur_row, cur_col, store[0], store[1]);
        			if (gap > 1000) continue;
        			
        			deque.add(new int[] {store[0], store[1]});
        			visited.put(getHash(store[0], store[1]),  true);
        		}
        	}
        	
        	
        	if (flg) System.out.println("happy");
        	else System.out.println("sad");
        		
        }
        
    }  
    
    static long getDistance(long fromRow, long fromCol, long toRow, long toCol) {
    	return Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol);
    }
    
    static Long getHash(long row, long col) {
    	row += 32768;
    	col += 32768;
    	long tmp = row * 100000;
    	tmp += col;
    	return tmp;
    }
}