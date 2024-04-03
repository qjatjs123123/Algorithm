
import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M;
    static HashMap<Integer, ArrayList<Integer>> forward;
    static HashMap<Integer, ArrayList<Integer>> backward;
   
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());
        
        for (int t = 1; t <= T; t++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
        	
        	forward = new HashMap<>();
        	backward = new HashMap<>();
        	
        	for (int i = 0; i < M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		
        		if (!forward.containsKey(from)) {
        			ArrayList<Integer> list = new ArrayList<>();
        			list.add(to);
        			forward.put(from, list);
        		} else {
        			ArrayList<Integer> list = forward.get(from);
        			list.add(to);
        			forward.put(from, list);
        		}
        		
        		if (!backward.containsKey(to)) {
        			ArrayList<Integer> list = new ArrayList<>();
        			list.add(from);
        			backward.put(to, list);
        		} else {
        			ArrayList<Integer> list = backward.get(to);
        			list.add(from);
        			backward.put(to, list);
        		}
        	}
        	int ans = 0;
        	for (int n = 1; n <= N; n++) {
        		int cnt = 1;
    			cnt += bfs(n, forward);
    			cnt += bfs(n, backward);
    			if (cnt == N) ans += 1;
    		}
        	System.out.println("#" + t + " " + ans);
        }
        
    }  
    
    static int bfs(int num, HashMap<Integer, ArrayList<Integer>> map) {
    	
    	boolean[] visited = new boolean[N + 1];
    	int total = 0;
    	Deque<Integer> deque = new LinkedList<>();
    	deque.add(num);
    	visited[num] = true;
    	
    	while (!deque.isEmpty()) {
    		int cur_num = deque.pollFirst();
    		
    		if (!map.containsKey(cur_num)) continue;
    		ArrayList<Integer> new_list = map.get(cur_num);
    		
    		for (int new_num : new_list) {
    			if (visited[new_num]) continue;
    			total += 1;
    			visited[new_num] = true;
    			deque.add(new_num);
    		}
    	}
    	
    	
    	return total;
    }
    
}