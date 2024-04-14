import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int N, M, W;
    static HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
    static int[][] road;
    static int[][] hole;
    static ArrayList<Integer> holeList;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
        	
        	road = new int[N + 1][N + 1];
        	hole = new int[N + 1][N + 1];
        	holeList = new ArrayList<>();
        	
        	for (int row = 0; row <= N; row++) {
        		for (int col = 0; col <= N; col++) {
        			if (row == col) {
        				road[row][col] = 0;
        				hole[row][col] = 0;
        				continue;
        			}
        			road[row][col] = 9999999;
        			hole[row][col] = 9999999;
        		}
        	}
        	
        	for (int i = 0; i < M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		int time = Integer.parseInt(st.nextToken());
        		
        		road[from][to] = Math.min(road[from][to], time);
        		road[to][from] = Math.min(road[to][from], time);
        		hole[from][to] = Math.min(hole[from][to], time);
        		hole[to][from] = Math.min(hole[to][from], time);
        		
        	}
        	
        	for (int i = 0; i < W; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		int time = Integer.parseInt(st.nextToken());
        		hole[from][to] = Math.min(hole[from][to], -time);
        		holeList.add(from);

        	}
        	floyd(road);
        	floyd(hole);
        	
        	boolean ans = false;
        	for (int from = 1; from <= N; from++) {
        		for (int to : holeList) {
        			int roadDistance = road[from][to];
        			int holeDistance = hole[to][from];
        			int gap = roadDistance + holeDistance;
        			
        			if (gap < 0) {
        				ans = true;
        				break;
        			}
        		}
        		if (ans) break;
        	}

        	if (ans) System.out.println("YES");
        	else System.out.println("NO");
        }
    }  
    
    static void floyd(int[][] arr) {
    	for (int mid = 1; mid <= N; mid++) {
    		for (int from = 1; from <= N; from++) {
    			if (mid == from) continue;
    			for (int to = 1; to <= N; to++) {
    				if (mid == to) continue;
    				
    				arr[from][to] = Math.min(arr[from][to], arr[from][mid] + arr[mid][to]);
    			}
    		}
    	}
    }
}


